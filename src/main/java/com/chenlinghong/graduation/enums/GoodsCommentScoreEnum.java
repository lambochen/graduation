package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 商品评论 评分枚举
 * @Author chenlinghong
 * @Date 2019/4/25 22:57
 * @Version V1.0
 */
@Getter
public enum GoodsCommentScoreEnum {

    ONE(1, "差评"),
    TWO(2, ""),
    THREE(3, "中评"),
    FOUR(4, ""),
    FIVE(5, "好评"),

    ;


    /**
     * 评分
     */
    private int score;

    /**
     * message
     */
    private String msg;

    GoodsCommentScoreEnum(int score, String msg) {
        this.score = score;
        this.msg = msg;
    }
}
