package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.ShoppingCart;

import java.util.List;

/**
 * @Description 购物车
 * @Author chenlinghong
 * @Date 2019/4/15 1:41
 * @Version V1.0
 */
public interface ShoppingCartService extends IBaseService<ShoppingCart> {

    /**
     * 根据ID批量删除
     *
     * @param idList
     * @return
     */
    int deleteByIdList(List<Long> idList, long userId);

    /**
     * 根据用户获取
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<ShoppingCart> listByUser(long userId, long pageNo, long pageSize);

}
