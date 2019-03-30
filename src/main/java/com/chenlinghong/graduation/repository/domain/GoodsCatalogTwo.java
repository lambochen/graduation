package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 商品二级目录
 * @Author chenlinghong
 * @Date 2019/3/24 19:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCatalogTwo extends BaseDomain {

    /**
     * 目录名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 一级目录ID
     */
    private Long catalogOneId;

    /**
     * 二级目录列表
     */
    private List<GoodsCatalogTwo> catalogTwoList;

    public GoodsCatalogTwo(String name, long catalogOneId) {
        this.name = name;
        this.catalogOneId = catalogOneId;
    }

}