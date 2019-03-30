package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品订单表
 * @Author chenlinghong
 * @Date 2019/3/30 16:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrder extends BaseDomain {

    private static final long serialVersionUID = -2631341617452349166L;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 价格
     */
    private Double price;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 国家，邮件地址
     */
    private String postCountry;

    /**
     * 省
     */
    private String postProvince;

    /**
     * 城市
     */
    private String postCity;

    /**
     * 具体地址
     */
    private String postPosition;
}
