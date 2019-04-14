package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 模块枚举
 * @Author chenlinghong
 * @Date 2019/2/24 16:56
 **/
@Getter
public enum ModularEnum {

    GOODS(1, "商品模块", "goods"),
    USER(2, "用户模块", "user"),

    ;

    private int code;
    private String name;
    private String filePath;

    ModularEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    ModularEnum(int code, String name, String filePath) {
        this.code = code;
        this.name = name;
        this.filePath = filePath;
    }
}
