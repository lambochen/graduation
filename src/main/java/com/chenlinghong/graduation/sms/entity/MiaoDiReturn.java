package com.chenlinghong.graduation.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/1/24 17:41
 **/
@Data
@NoArgsConstructor
public class MiaoDiReturn {

    /**
     * 请求状态码
     */
    private String respCode;

    /**
     * 描述
     */
    private String respDesc;

    /**
     * 表示验证码通过短信发送失败的条数
     */
    private String failCount;

    /**
     * 失败列表，包含失败号码、失败原因
     */
    private Object failList;

    /**
     * 短信标示符，一个由32个字符组成的短信唯一标志符
     */
    private String smsId;

}
