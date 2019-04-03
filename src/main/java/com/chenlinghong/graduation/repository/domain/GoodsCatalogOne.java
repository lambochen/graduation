package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 商品一级目录
 * @Author chenlinghong
 * @Date 2019/3/24 18:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCatalogOne extends BaseDomain {

    private static final long serialVersionUID = -5053347798176292607L;
    /**
     * 目录名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 二级目录列表
     */
    private List<GoodsCatalogTwo> catalogTwoList;

    public GoodsCatalogOne(String name) {
        this.name = name;
    }
}
