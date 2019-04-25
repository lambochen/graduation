package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.repository.domain.UserBehavior;

/**
 * @Description 用户行为Service
 * @Author chenlinghong
 * @Date 2019/4/25 18:24
 * @Version V1.0
 */
public interface UserBehaviorService extends IBaseService<UserBehavior> {

    /**
     * 新增用户行为
     *
     * @param goodsId
     * @param userId
     * @param behavior
     * @return
     */
    int insert(long goodsId, long userId, int behavior);

    /**
     * 新增用户行为
     *
     * @param goodsId
     * @param userId
     * @param behaviorEnum
     * @return
     */
    int insert(long goodsId, long userId, UserBehaviorEnum behaviorEnum);


}
