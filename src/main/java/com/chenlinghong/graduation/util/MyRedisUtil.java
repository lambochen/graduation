package com.chenlinghong.graduation.util;

import com.alibaba.fastjson.JSON;
import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.common.redis.RedisUtil;
import com.chenlinghong.graduation.constant.RedisConstant;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public String put(UserVo userVo) {
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
        return redisKey;
    }

}
