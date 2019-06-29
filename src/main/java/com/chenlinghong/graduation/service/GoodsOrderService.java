package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;

/**
 * @Description 商品订单
 * @Author chenlinghong
 * @Date 2019/4/15 2:47
 * @Version V1.0
 */
public interface GoodsOrderService extends IBaseService<GoodsOrder> {

    /**
     * 根据用户获取订单列表
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsOrder> listByUser(long userId, long pageNo, long pageSize);

    /**
     * 根据商品ID获取订单列表
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<GoodsOrder> listByGoods(long goodsId, long pageNo, long pageSize);
}
