package com.chenlinghong.graduation.service;

/**
 * @Author:DxlinY
 * @Description：
 * @Date: 2019/4/9
 * @Time: 14:41
 */

import com.chenlinghong.graduation.repository.domain.Goods;

import java.util.List;

/**
 * 商品信息相关service
 */
public interface GoodsService {
    /**
     * 根据商品一级目录ID获取商品列表
     *
     * @param goodsCatalogOneId
     * @return
     */
    List<Goods> listByGoodsCatalogOneId(Long goodsCatalogOneId);

    /**
     * 根据商品二级目录ID获取商品列表
     *
     * @param goodsCatalogTwoId
     * @return
     */
    List<Goods> listByGoodsCatalogTwoId(Long goodsCatalogTwoId);
}
