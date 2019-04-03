package com.chenlinghong.graduation.sms;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description 验证码通知短信接口
 * @Author chenlinghong
 * @Date 2019/1/24 16:26
 **/
@Slf4j
public class IndustrySMS {

    /**
     * 发送短信验证码
     */
    private static final String operation = "/industrySMS/sendSMS";

    /**
     * 账号ID
     */
    private static final String accountSid = SmsConfiguration.ACCOUNT_SID;

    /**
     * 短信头部
     */
    private static final String smsHead = "【小农娱乐科技】您的验证码为";

    /**
     * 短信尾部  固定2分钟
     */
    private static final String smsTail = "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";

    /**
     * 验证码发送短信
     *
     * @param telephone 电话号码
     * @param smsCode   短信验证码
     */
    public static void execute(String telephone, String smsCode) {
        /**
         * 短信内容
         */
        StringBuilder smsContent = new StringBuilder(smsHead);
        smsContent.append(smsCode).append(smsTail);
        String tmpContent;
        try {
            tmpContent = URLEncoder.encode(smsContent.toString(), HttpUtil.charsetName);
        } catch (UnsupportedEncodingException e) {
            log.error("URLEncoder.encode(), telephone={},smsCode={}, data={}", telephone, smsCode, e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        String url = SmsConfiguration.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + telephone + "&smsContent=" + tmpContent
                + HttpUtil.createCommonParam();
        /**
         * 提交请求
         */
        String result = HttpUtil.post(url, body);
        log.info("IndustrySMS.execute result={},telephone={},smsCode={}", result, telephone, smsCode);
    }
}
