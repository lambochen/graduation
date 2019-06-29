package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.repository.domain.UserBehavior;

import java.util.Date;
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

    /**
     * 通过user、goodsId、startTime获取
     *
     * @param userId
     * @param goodsId
     * @param startTime
     * @return
     */
    PageDto<UserBehavior> listByUserAndGoodsAndStartTime(long userId, long goodsId, Date startTime);

    /**
     * 根据用户获取所有行为记录
     *
     * @param userId
     * @param startTime 开始时间
     * @return
     */
    PageDto<UserBehavior> listByUserAndStartTime(long userId, Date startTime);

    /**
     * 根据用户获取
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<UserBehavior> listByUser(long userId, long pageNo, long pageSize);
}
