package com.chenlinghong.graduation.service;

/**
 * @Author:DxlinY
 * @Description：商品相关service
 * @Date: 2019/4/9
 * @Time: 14:41
 */

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Goods;
import org.springframework.data.domain.Page;

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
    PageDto listByGoodsCatalogOneId(long goodsCatalogOneId);

    /**
     * 根据商品二级目录ID获取商品列表
     *
     * @param goodsCatalogTwoId
     * @return
     */
    PageDto listByGoodsCatalogTwoId(long goodsCatalogTwoId);

    /**
     * 通过id查找商品详情
     *
     * @param id
     * @return
     */
    Goods getGoodsById(long id);
}
