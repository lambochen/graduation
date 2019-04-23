package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品订单DAO
 * @Author chenlinghong
 * @Date 2019/3/30 16:52
 **/
public interface GoodsOrderDao extends IBaseDao<GoodsOrder> {


    /**
     * 根据商品ID获取订单列表
     *
     * @param goodsId 商品ID
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsOrder> listByGoods(@Param("goodsId") long goodsId,
                                 @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据商品ID获取记录数
     *
     * @param goodsId
     * @return
     */
    long countByGoods(@Param("goodsId") long goodsId);

    /**
     * 根据用户获取订单列表
     *
     * @param userId 用户ID
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsOrder> listByUser(@Param("userId") long userId,
                                @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据用户获取记录数
     *
     * @param userId
     * @return
     */
    long countByUser(@Param("userId") long userId);


}
