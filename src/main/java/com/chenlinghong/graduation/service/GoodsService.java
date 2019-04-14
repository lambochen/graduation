package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Goods;

/**
 * @Description 商品基本信息
 * @Author chenlinghong
 * @Date 2019/4/14 15:35
 * @Version V1.0
 */
public interface GoodsService extends IBaseService<Goods> {

    /**
     * 根据一级目录ID获取商品列表
     *
     * @param catalogOneId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<Goods> listByCatalogOne(int catalogOneId, long pageNo, long pageSize);

    /**
     * 根据二级目录ID获取商品列表
     *
     * @param catalogTwoId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<Goods> listByCatalogTwo(int catalogTwoId, long pageNo, long pageSize);


}
