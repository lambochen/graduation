package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 购物车
 * @Author chenlinghong
 * @Date 2019/4/2 18:06
 **/
public interface ShoppingCartDao extends IBaseDao<ShoppingCart> {


    /**
     * 根据ID批量删除
     * @param idList
     * @return
     */
    int deleteByIdList(List<Long> idList, long userId);

    /**
     * 根据用户分页获取
     * @param userId
     * @param offset
     * @param rows
     * @return
     */
    List<ShoppingCart> listByUser(@Param("userId") long userId,
                                  @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据用户分页获取
     * @param userId
     * @return
     */
    long countByUser(long userId);

    ShoppingCart getByUserGoods(@Param("goodsId") long goodsId, @Param("userId") long userId);

    /**
     * 更新数量
     * @param id
     * @param count
     * @return
     */
    int updateCount(@Param("id") long id, @Param("count") int count);

}
