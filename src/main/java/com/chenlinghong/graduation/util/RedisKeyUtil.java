package com.chenlinghong.graduation.util;

import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.constant.RedisConstant;
import com.chenlinghong.graduation.repository.domain.BaseDomain;
import com.chenlinghong.graduation.repository.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @Description redis key设计
 * @Author chenlinghong
 * @Date 2019/4/5 13:35
 * @Version V1.0
 */
@Service
public class RedisKeyUtil<T> {

    /**
     * 生成redis key，通过domain对象
     * redisKey = {className}:{id}
     *
     * @param data
     * @return
     */
    public String generateKey(T data) {
        if (data == null) {
            return null;
        }
        String className = getSimpleNameForDomain(data);
        if (className == null) {
            return null;
        }
        StringBuffer redisKey = new StringBuffer(className);
        redisKey.append(RedisConstant.SEPARATOR);
        // 将data强转为BaseDomain
        BaseDomain dataDomain = (BaseDomain) data;
        redisKey.append(dataDomain.getId());
        return redisKey.toString();
    }

    /**
     * 生成redis key，通过domain对象
     * redisKey = {className}:{telephone}
     *
     * @param data
     * @param telephone
     * @return
     */
    public String generateKey(T data, String telephone) {
        if (data == null || StringUtils.isBlank(telephone)) {
            return null;
        }
        String className = getSimpleNameForDomain(data);
        if (className == null) {
            return null;
        }
        StringBuffer redisKey = new StringBuffer(className);
        redisKey.append(RedisConstant.SEPARATOR).append(telephone);
        return redisKey.toString();
    }

    /**
     * 生成redis key
     * 用户基本信息视图对象
     * redisKey = user_info:user:{telephone}
     *
     * @param userVo
     * @return
     */
    public String generateKey(UserVo userVo) {
        if (userVo == null) {
            return null;
        }
        // 获取userId
        User userInfo = userVo.getUserInfo();
        if (userInfo == null) {
            return null;
        }
        String telephone = userInfo.getTelephone();
        return generateKeyForUserVo(telephone);
    }

    /**
     * redis key: 用户视图对象
     * redisKey = user_info:user:{telephone}
     *
     * @param telephone
     * @return
     */
    public String generateKeyForUserVo(String telephone) {
        StringBuffer redisKey = new StringBuffer();
        redisKey.append(RedisConstant.USER_INFO).append(RedisConstant.SEPARATOR)
                .append(RedisConstant.USER).append(RedisConstant.SEPARATOR).append(telephone);
        return redisKey.toString();
    }

    /**
     * 生成redis key。主要针对电话号码进行存储短信验证码
     * redisKey = telephone:{telephone}
     *
     * @param telephone
     * @return
     */
    public String generateKeyForSms(String telephone) {
        StringBuffer redisKey = new StringBuffer();
        redisKey.append(RedisConstant.TELEPHONE).append(RedisConstant.SEPARATOR).append(telephone);
        return redisKey.toString();
    }

    /**
     * 推荐商品排名
     *
     * @return
     */
    public String generateKeyForRecommendRankingGoods() {
        return RedisConstant.RECOMMEND_RANKING_GOODS;
    }

    /**
     * 获取domain对象的类名
     *
     * @param data
     * @return
     */
    private String getSimpleNameForDomain(T data) {
        // data 是 domain领域对象
        if (data instanceof BaseDomain) {
            /**
             * 通过反射，获取到data的类名，实际key的设计为：className:id
             */
            Class clazz = data.getClass();
            String className = clazz.getSimpleName();
            return className;
        }
        /**
         * 暂不提供其它类的redis key设计
         */
        return null;
    }

}
