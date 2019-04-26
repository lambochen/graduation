package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserPreference;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户偏好
 * @Author chenlinghong
 * @Date 2019/4/24 9:14
 * @Version V1.0
 */
public interface UserPreferenceDao extends IBaseDao<UserPreference> {

    /**
     * 根据用户、商品获取记录数
     *
     * @param userId
     * @param goodsId
     * @return
     */
    int countByUserAndGoods(@Param("userId") long userId, @Param("goodsId") long goodsId);

}
