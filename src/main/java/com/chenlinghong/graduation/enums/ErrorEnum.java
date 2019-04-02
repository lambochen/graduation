package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 错误码枚举类
 * @Author chenlinghong
 * @Date 2019/3/13 21:04
 **/
@Getter
public enum ErrorEnum {

    SUCCESS(0, "请求成功"),


    /**
     * 1XXX 短信验证码相关
     */
    SMS_SEND_ERROR(1000, "发送短信验证码失败"),
    NO_SMS(1001, "短信验证码不存在，请先发送短信验证码。。。"),
    SMS_TIMEOUT(1002, "短信验证码无效,请重新获取"),
    SMS_ERROR(1003, "短信验证码不正确"),

    /**
     * 2XXX 其他错误
     */
    PARAM_IS_NULL(2000, "参数为空"),
    PARAM_ILLEGAL(2001, "参数非法"),
    TELEPHONE_ILLEGAL(2002, "电话号码非法"),


    ;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
