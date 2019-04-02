package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 错误码枚举类
 * @Author chenlinghong
 * @Date 2019/3/13 21:04
 **/
@Getter
public enum  ErrorEnum {

    SUCCESS(0, "请求成功"),


    /**
     * 6XXX 短信验证码相关
     */
    SMS_SEND_ERROR(6000, "发送短信验证码失败"),
    NO_SMS(6001, "短信验证码不存在，请先发送短信验证码。。。"),
    SMS_TIMEOUT(6002, "短信验证码无效,请重新获取"),
    SMS_ERROR(6003, "短信验证码不正确"),

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
