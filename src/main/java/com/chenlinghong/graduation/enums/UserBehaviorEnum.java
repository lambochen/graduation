package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 用户行为枚举类
 * @Author chenlinghong
 * @Date 2019/4/25 18:31
 * @Version V1.0
 */
@Getter
public enum UserBehaviorEnum {

    //行为: 1点击, 2加入购物车, 3购买, 4评价-1, 5评价-2, 6评价-3, 7评价-4， 8评价-5

    CLICK(1, "点击", 2),
    ADD_TO_CART(2, "加入购物车", 5),
    PURCHASE(3, "购买", 4),
    COMMENT_SCORE_1(4, "评价-1分", 1),
    COMMENT_SCORE_2(5, "评价-2分", 2),
    COMMENT_SCORE_3(6, "评价-3分", 3),
    COMMENT_SCORE_4(7, "评价-4分", 4),
    COMMENT_SCORE_5(8, "评价-5分", 5),
    SEARCH(9, "搜索", 3),
    PATTERN_RECOGNITION(10, "模式识别", 1),

    ;

    /**
     * 行为码 对应数据库存储数值
     */
    private int code;

    /**
     * message
     */
    private String msg;

    /**
     * 因子，指用户行为对用户偏好的影响因素，值越大说明影响越大
     * TODO 后期将会采用每个用户影响因子不一样，进行训练调参
     */
    private Integer factor;

    UserBehaviorEnum(int code, String msg, int factor) {
        this.code = code;
        this.msg = msg;
        this.factor = factor;
    }
}
