package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.repository.domain.UserPreference;

/**
 * @Description 用户偏好Service
 * @Author chenlinghong
 * @Date 2019/4/26 18:04
 * @Version V1.0
 */
public interface UserPreferenceService extends IBaseService<UserPreference> {

    /**
     * 通过用户ID、商品ID获取
     *
     * @param userId
     * @param goodsId
     * @return
     */
    UserPreference getByUserAndGoods(long userId, long goodsId);

    /**
     * 通过用户ID、商品ID获取记录数
     *
     * @param userId
     * @param goodsId
     * @return
     */
    int countByUserAndGoods(long userId, long goodsId);

    /**
     * 是否是有效的用户偏好数据
     *
     * @param userId
     * @param goodsId
     * @return
     */
    boolean isAliveUserPreference(long userId, long goodsId);

}

