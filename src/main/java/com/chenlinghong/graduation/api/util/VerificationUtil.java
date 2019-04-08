package com.chenlinghong.graduation.api.util;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.TelephoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 校验工具类
 * @Author chenlinghong
 * @Date 2019/4/2 23:04
 **/
public final class VerificationUtil {

    @Autowired
    private MyRedisUtil redisUtil;

    /**
     * 校验短信验证码
     *
     * @param telephone 手机号码
     * @param smsCode   短信验证码
     * @return
     */
    public boolean checkSMSCode(String telephone, String smsCode) {
        if (StringUtils.isBlank(telephone) || StringUtils.isBlank(smsCode)) {
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        /**
         * 格式化，去除两端空格
         */
        telephone = telephone.trim();
        smsCode = smsCode.trim();
        if (TelephoneUtil.isNotPhoneLegal(telephone)) {
            /**
             * 电话号码不合法
             */
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return isAliveCode(telephone, smsCode);
    }

    /**
     * 是否是有效的验证码
     *
     * @param telephone 手机号码
     * @param smsCode   验证码
     * @return
     */
    private boolean isAliveCode(String telephone, String smsCode) {
        // 获取redis中存储的验证码
        String redisSmsCode = redisUtil.getSmsCode(telephone);
        if (smsCode.equalsIgnoreCase(redisSmsCode)) {
            return true;
        }
        return false;
    }

}
