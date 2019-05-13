package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 购物车
 * @Author chenlinghong
 * @Date 2019/4/2 18:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart extends BaseDomain {

    private static final long serialVersionUID = 5687175064118143545L;

    /**
     * 商品ID
     */
    private Long goodsId;

    private Goods goods;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 数量
     */
    private Integer count;

}
