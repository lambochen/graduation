package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：二级目录service层
 * @Date: 2019/4/9
 * @Time: 15:23
 */
public interface GoodsCatalogTwoService {
    /**
     * 根据商品一级目录ID获取二级目录列表
     *
     * @param goodsCatalogOneId
     * @return
     */
    PageDto listByGoodsCatalogOneId(Long goodsCatalogOneId);
}
