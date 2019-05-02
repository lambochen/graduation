package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品推荐队列
 * @Author chenlinghong
 * @Date 2019/5/2 21:35
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendQueueGoods extends BaseDomain {

    private static final long serialVersionUID = 6266448220551663689L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品基本信息
     */
    private Goods goods;

    /**
     * 推荐类型
     */
    private Integer recommendType;
}
