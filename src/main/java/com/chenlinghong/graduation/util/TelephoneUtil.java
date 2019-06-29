package com.chenlinghong.graduation.util;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.sms.IndustrySMS;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Description 电话号码相关工具
 * @Author chenlinghong
 * @Date 2019/4/2 21:27
 **/
public final class TelephoneUtil {

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 非法电话号码
     *
     * @param telephone
     * @return
     */
    public static boolean isNotPhoneLegal(String telephone) {
        return !isPhoneLegal(telephone);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNotChinaPhoneLegal(String str) {
        return !isChinaPhoneLegal(str);
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNotHKPhoneLegal(String str) {
        return !isHKPhoneLegal(str);
    }

    /**
     * 发送短信验证码
     *
     * @param telephone 电话号码
     * @return
     */
    public static String sendSMSCode(String telephone) {
        if (StringUtils.isEmpty(telephone)) {
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        telephone = telephone.trim();
        if (isNotPhoneLegal(telephone)) {
            throw new BusinessException(ErrorEnum.TELEPHONE_ILLEGAL);
        }
        String smsCode = generateSmsCode();
        /**
         * 调用云厂商API，发送短信验证
         */
        IndustrySMS.execute(telephone, smsCode);
        return smsCode;
    }

    /**
     * 生成短信验证码
     *
     * @return
     */
    private static String generateSmsCode() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append("" + (int) (Math.random() * 10));
        }
        return result.toString();
    }

}
