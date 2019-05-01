package com.chenlinghong.graduation.calculator;

import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;

import java.util.List;

/**
 * TODO 目前该计算类仅支持单机版、权重因子以确定的情况
 *
 * @Description 用户-商品偏好 计算类
 * @Author chenlinghong
 * @Date 2019/4/26 13:29
 * @Version V1.0
 */
// @Async(value = AsyncNameConstant.MICROSCOPE)
public interface UserGoodsPreferenceCalculation extends PreferenceCalculation {

    /**
     * 计算用户偏好数值
     *
     * @param behavior
     * @return
     */
    Integer calculation(int behavior);

    /**
     * 计算用户偏好
     *
     * @param userBehavior
     * @return
     */
    UserPreference calculation(UserBehavior userBehavior);

    /**
     * 计算用户偏好
     *
     * @param userId   用户ID
     * @param goodsId  商品ID
     * @param behavior 行为
     * @return
     */
    UserPreference calculation(long userId, long goodsId, int behavior);

    /**
     * 计算用户偏好列表, 数据：同一用户不同商品的行为信息
     *
     * @param behaviorList 行为列表，存在不同商品，要求是同一个用户的
     * @return
     */
    /**
     * 1、检查列表中是否存在非法数据：其它用户数据。若存在则直接返回空列表
     * 2、按照商品ID对列表进行排序
     * 3、抽离出每一个商品的用户行为信息，计算用户偏好
     * 4、组装列表，返回结果
     */
    List<UserPreference> calculation(List<UserBehavior> behaviorList);


    /**
     * 计算用户偏好,
     * 数据要求：单个用户的行为数据、行为数据经过goodsId排序
     *
     * @param behaviorList
     * @return
     */
    List<UserPreference> calculationByOneUserAndSortedBehavior(List<UserBehavior> behaviorList);


    /**
     * 计算用户偏好
     *
     * @param behaviorList 用户行为列表，要求为同一个用户对某一个商品的行为列表
     * @return
     */
    UserPreference calculationOne(List<UserBehavior> behaviorList);

    /**
     * 通过用户行为列表计算具体偏好值
     *
     * @param behaviorList
     * @return
     */
    Integer calculationByBehavior(List<Integer> behaviorList);


}
