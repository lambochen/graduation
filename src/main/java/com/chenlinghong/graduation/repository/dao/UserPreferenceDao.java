package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserPreference;
import lombok.NonNull;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用户偏好
 * @Author chenlinghong
 * @Date 2019/4/24 9:14
 * @Version V1.0
 */
public interface UserPreferenceDao extends IBaseDao<UserPreference> {

    /**
     * 批量新增
     *
     * @param preferenceList
     * @return
     */
    int insertBatch(@Param("preferenceList") List<UserPreference> preferenceList);

    /**
     * 根据用户、商品获取记录数
     *
     * @param userId
     * @param goodsId
     * @return
     */
    int countByUserAndGoods(@Param("userId") long userId, @Param("goodsId") long goodsId);

    /**
     * 通过用户ID、商户ID获取
     *
     * @param userId
     * @param goodsId
     * @return
     */
    UserPreference getByUserAndGoods(@Param("userId") long userId, @Param("goodsId") long goodsId);

    /**
     * 批量更新
     *
     * @param preferenceList
     * @return
     */
    int updateBatch(@Param("preferenceList") @NonNull List<UserPreference> preferenceList);

}
