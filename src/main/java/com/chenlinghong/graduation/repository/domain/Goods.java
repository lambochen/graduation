package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品
 * @Author chenlinghong
 * @Date 2019/3/30 11:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends BaseDomain {

    private static final long serialVersionUID = 6113025786619933573L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 一级目录ID
     */
    private Integer catalogOneId;

    /**
     * 二级目录ID
     */
    private Integer catalogTwoId;

    /**
     * 商品信息
     */
    private String goodsInfo;

    /**
     * 价格
     */
    private Double price;

    /**
     * 备注
     */
    private String remarks;

}
