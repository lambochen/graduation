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

}
