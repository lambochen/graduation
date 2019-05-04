package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;

/**
 * @Description 商品目录
 * @Author chenlinghong
 * @Date 2019/4/14 14:27
 * @Version V1.0
 */
public interface GoodsCatalogService {

    /**
     * 分页获取商品目录
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsCatalogOne> listAll(int pageNo, int pageSize);

    /**
     * 根据一级目录ID获取二级目录
     *
     * @param catalogOneId 一级目录ID
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsCatalogTwo> listByCatalogOne(int catalogOneId, int pageNo, int pageSize);

    /**
     * 根据时令获取列表
     *
     * @param currentSeason
     * @return
     */
    PageDto<GoodsCatalogTwo> listBySeason(int currentSeason);
}
