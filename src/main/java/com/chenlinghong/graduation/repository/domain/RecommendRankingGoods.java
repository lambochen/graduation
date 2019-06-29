package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 物品推荐排行榜
 * @Author chenlinghong
 * @Date 2019/5/2 21:04
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRankingGoods extends BaseDomain {

    private static final long serialVersionUID = -1675652653930902106L;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品ID信息
     */
    private Goods goods;

    /**
     * 排行榜
     */
    private Integer ranking;
}
