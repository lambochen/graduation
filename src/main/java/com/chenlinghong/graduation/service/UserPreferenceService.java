package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.repository.domain.UserPreference;
import lombok.NonNull;

import java.util.List;

/**
 * @Description 用户偏好Service
 * @Author chenlinghong
 * @Date 2019/4/26 18:04
 * @Version V1.0
 */
public interface UserPreferenceService extends IBaseService<UserPreference> {

    /**
     * 批量新增
     *
     * @param preferenceList
     * @return
     */
    int insert(@NonNull List<UserPreference> preferenceList);

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

    /**
     * 无效的用户偏好数据
     *
     * @param userId
     * @param goodsId
     * @return
     */
    boolean isNotAliveUserPreference(long userId, long goodsId);

    /**
     * 是否是有效的用户偏好数据
     *
     * @param preference
     * @return
     */
    boolean isAliveUserPreference(@NonNull UserPreference preference);

    /**
     * 无效的用户偏好数据
     * @param preference
     * @return
     */
    boolean isNotAliveUserPreference(@NonNull UserPreference preference);

    /**
     * 批量更新
     *
     * @param preferenceList
     * @return
     */
    int update(@NonNull List<UserPreference> preferenceList);


}

