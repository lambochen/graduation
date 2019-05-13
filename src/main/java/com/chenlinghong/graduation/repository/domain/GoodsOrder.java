package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "商品ID不能为空")
    private Long goodsId;

    /**
     * 商品基本信息
     */
    private Goods goods;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商户ID
     */
    // private Long businessId;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
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

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @Range(min = 1, message = "购买数量只能为正数")
    private Integer number;
}
