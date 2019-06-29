package com.chenlinghong.graduation.enums;

import lombok.Getter;

/**
 * @Description 推荐类型枚举
 * @Author chenlinghong
 * @Date 2019/5/2 21:37
 * @Version V1.0
 */
@Getter
public enum RecommendTypeEnum {

    USER_BASED_RECOMMEND(1, "基于用户的协同过滤推荐"),
    ITEM_BASED_RECOMMEND(2, "基于物品的协同过滤推荐"),
    SLOPE_ONE_RECOMMEND(3, "SlopeOne协同过滤推荐"),
    POPULAR_RECOMMEND(4, "热门推荐"),
    SEASON_RECOMMEND(5, "时令推荐"),
    USER_TAG_BASED_RECOMMEND(6, "基于用户标签的推荐");

    /**
     * 类型编码
     */
    private int code;

    /**
     * 说明
     */
    private String msg;

    RecommendTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
