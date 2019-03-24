package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 商品一级目录
 * @Author chenlinghong
 * @Date 2019/3/24 18:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCatalogOne extends BaseDomain {

    /**
     * 目录名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    public GoodsCatalogOne(String name) {
        this.name = name;
    }
}
