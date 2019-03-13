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
