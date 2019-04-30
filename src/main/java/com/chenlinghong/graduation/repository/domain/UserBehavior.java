package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户行为表
 * @Author chenlinghong
 * @Date 2019/4/24 9:09
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBehavior extends BaseDomain {

    private static final long serialVersionUID = 1616767221060293312L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 物品ID
     */
    private Long goodsId;

    /**
     * 商品对象
     */
    private Goods goods;

    /**
     * 行为: 1点击, 2加入购物车, 3购买, 4评价-1, 5评价-2, 6评价-3, 7评价-4， 8评价-5
     */
    private Integer behavior;

    public UserBehavior(long userId, long goodsId, int behavior) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.behavior = behavior;
    }
}
