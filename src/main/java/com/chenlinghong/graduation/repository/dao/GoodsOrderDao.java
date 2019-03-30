package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品订单DAO
 * @Author chenlinghong
 * @Date 2019/3/30 16:52
 **/
public interface GoodsOrderDao {

    /**
     * 新增订单
     *
     * @param goodsOrder
     * @return
     */
    int insert(GoodsOrder goodsOrder);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    GoodsOrder getById(long id);

    /**
     * 分页获取所有订单信息
     *
     * @param offset
     * @param rows
     * @return
     */
    List<GoodsOrder> listAll(@Param("offset") long offset, @Param("rows") long rows);

    /**
     * 获取所有记录数
     *
     * @return
     */
    int count();

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

    /**
     * 更新订单基本信息     TODO 后期考虑是否使用该接口,暂不实现
     *
     * @param goodsOrder
     * @return
     */
    int update(GoodsOrder goodsOrder);

}
