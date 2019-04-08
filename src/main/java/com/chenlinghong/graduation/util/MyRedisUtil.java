package com.chenlinghong.graduation.util;

import com.alibaba.fastjson.JSON;
import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.redis.RedisUtil;
import com.chenlinghong.graduation.constant.RedisConstant;
import com.chenlinghong.graduation.repository.domain.User;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description 该项目专用redis 工具类
 * @Author chenlinghong
 * @Date 2019/4/6 17:35
 * @Version V1.0
 */
@Slf4j
@Service
public class MyRedisUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    /**
     * 用户视图对象写入redis。
     * userVo对象采用hash存储，其属性对象采用string存储
     * <p>
     * userVo : {
     * user : {},
     * // TODO 待完善
     * }
     *
     * @param userVo 用户视图对象
     * @return 该对象在redis存储中的key，若为null则写入失败
     */
    @Async("asyncServiceExecutor")
    public String put(UserVo userVo) {
        log.info("MyRedisUtil#put(UserVo): beginning. userVo={}", userVo);
        if (userVo == null || userVo.getUserInfo() == null) {
            log.error("MyRedisUtil#put(userVo): param is null. userVo={}", userVo);
            return null;
        }
        // 获取redis key
        String redisKey = redisKeyUtil.generateKey(userVo);
        /**
         * 构造field:value map
         */
        Map<String, String> hashData = Maps.newLinkedHashMap();
        // 用户基本信息
        String user = JSON.toJSONString(userVo.getUserInfo());
        hashData.put(RedisConstant.USER, user);
        /**
         * TODO 其它属性
         */
        // 写入redis
        redisUtil.hPutAll(redisKey, hashData);
        log.info("MyRedisUtil#put(UserVo): ended. userVo={}", userVo);
        return redisKey;
    }


    /**
     * 短信验证码写入redis
     *
     * @param telephone 电话号码
     * @param smsCode   短信验证码
     * @return
     */
    @Async("asyncServiceExecutor")
    public String putSmsCode(String telephone, String smsCode) {
        log.info("MyRedisUtil#putSmsCode: beginning. telephone={}, smsCode={}", telephone, smsCode);
        // 生成redis key
        String redisKey = redisKeyUtil.generateKeyForSms(telephone);
        // 写入redis
        redisUtil.set(redisKey, smsCode);
        // 设置key 存活时间   10 * 60s
        redisUtil.expire(redisKey, 10 * 60L, TimeUnit.SECONDS);
        log.info("MyRedisUtil#putSmsCode: ended. telephone={}, smsCode={}", telephone, smsCode);
        return redisKey;
    }

    /**
     * 获取userVo
     *
     * @param telephone
     * @return
     */
    public UserVo getUserVo(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return null;
        }
        UserVo userVo = new UserVo();
        // 用户基本信息
        User user = getUserByTelephone(telephone);
        userVo.setUserInfo(user);
        /**
         * TODO 其它属性
         */
        return userVo;
    }

    /**
     * 获取User
     *
     * @param key
     * @return
     */
    public User getUser(String key) {
        String jsonUser = (String) redisUtil.hGet(key, RedisConstant.USER);
        return JSON.parseObject(jsonUser, User.class);
    }

    /**
     * 根据电话号码获取User
     *
     * @param telephone
     * @return
     */
    public User getUserByTelephone(String telephone) {
        String redisKey = redisKeyUtil.generateKeyForUserVo(telephone);
        return getUser(redisKey);
    }

    /**
     * 获取短信验证码
     *
     * @param telephone
     * @return
     */
    public String getSmsCode(String telephone) {
        // 生成redis key
        String redisKey = redisKeyUtil.generateKeyForSms(telephone);
        // 获取短信验证码
        return redisUtil.get(redisKey);
    }

}
