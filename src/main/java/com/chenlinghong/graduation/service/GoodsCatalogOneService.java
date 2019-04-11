package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：一级目录Service
 * @Date: 2019/4/9
 * @Time: 21:49
 */
public interface GoodsCatalogOneService {
    /**
     * 获取一级目录所有列表
     *
     * @return
     */
    PageDto listAllGoodsCatalogOne();
}
