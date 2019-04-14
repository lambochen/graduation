package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;

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

}
