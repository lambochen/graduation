package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.repository.domain.UserBehavior;

import java.util.List;

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
     * @param behaviorList
     * @return
     */
    int insert(List<UserBehavior> behaviorList);

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

    /**
     * 批量新增
     *
     * @param goodsId
     * @param userId
     * @param behavior
     * @param frequency
     * @return
     */
    int insert(long goodsId, long userId, int behavior, int frequency);

    /**
     * 批量新增
     *
     * @param goodsId
     * @param userId
     * @param behaviorEnum
     * @param frequency
     * @return
     */
    int insert(long goodsId, long userId, UserBehaviorEnum behaviorEnum, int frequency);


}
