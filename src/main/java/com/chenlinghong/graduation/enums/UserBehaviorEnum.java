package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 用户行为枚举类
 * @Author chenlinghong
 * @Date 2019/4/25 18:31
 * @Version V1.0
 */
@Getter
public enum  UserBehaviorEnum {

    //行为: 1点击, 2加入购物车, 3购买, 4评价-1, 5评价-2, 6评价-3, 7评价-4， 8评价-5

    CLICK(1,"点击"),
    ADD_TO_CART(2, "加入购物车"),
    PURCHASE(3, "购买"),
    COMMENT_SCORE_1(4,"评价-1分"),
    COMMENT_SCORE_2(5,"评价-2分"),
    COMMENT_SCORE_3(6,"评价-3分"),
    COMMENT_SCORE_4(7,"评价-4分"),
    COMMENT_SCORE_5(8,"评价-5分"),

    ;

    /**
     * 行为码 对应数据库存储数值
     */
    private int code;

    /**
     * message
     */
    private String msg;

    UserBehaviorEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
