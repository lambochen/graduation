package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 用户类型
 * @Author chenlinghong
 * @Date 2019/4/29 9:42
 * @Version V1.0
 */
@Getter
public enum CheckUserTypeEnum {

    NOT_USER(0, "非登录用户"),
    NEW_USER(1, "新用户"),
    OLD_USER(2, "老用户"),

    ;

    /**
     * 类型ＣＯＤＥ
     */
    private int code;

    /**
     * 类型说明
     */
    private String msg;

    CheckUserTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
