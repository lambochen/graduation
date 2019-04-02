package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 购物车
 * @Author chenlinghong
 * @Date 2019/4/2 18:06
 **/
public interface ShoppingCartDao {

    /**
     * 新增
     * @param shoppingCart
     * @return
     */
    int insert(ShoppingCart shoppingCart);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 根据ID批量删除 TODO 后期完善
     * @param idList
     * @return
     */
    // int deleteById(List<Long> idList);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    ShoppingCart getById(long id);

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
    int countByUser(long userId);

}
