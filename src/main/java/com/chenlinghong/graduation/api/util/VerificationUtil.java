package com.chenlinghong.graduation.api.util;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.util.TelephoneUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description 校验工具类
 * @Author chenlinghong
 * @Date 2019/4/2 23:04
 **/
public final class VerificationUtil {


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

    private boolean isAliveCode(String telephone, String smsCode) {
        // TODO redis中读取
        return true;
    }

}
