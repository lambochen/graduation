package com.chenlinghong.graduation.microscope.actuator;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;

import java.util.Date;

/**
 * @Description 用户偏好执行器
 * @Author chenlinghong
 * @Date 2019/4/26 15:19
 * @Version V1.0
 */
public interface UserGoodsPreferenceActuator extends PreferenceActuator {

    /**
     * 刷新用户偏好
     *
     * @param userPreference
     */
    void refresh(UserPreference userPreference);

    /**
     * 刷新用户偏好，所有商品均刷新，采用默认时间窗口
     *
     * @param userId
     */
    void refresh(long userId);

    /**
     * 刷新用户偏好，采用默认时间窗口
     *
     * @param userId  用户ID
     * @param goodsId 商品ID
     */
    void refresh(long userId, long goodsId);

    /**
     * 刷新用户偏好，抓取数据startTime
     *
     * @param userId    用户ID
     * @param goodsId   商户ID
     * @param startTime 开始时间
     */
    void refresh(long userId, long goodsId, Date startTime);

    /**
     * 刷新用户偏好
     *
     * @param userId
     * @param goodsId
     * @param preference 用户偏好新值
     */
    void refresh(long userId, long goodsId, int preference);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userBehavior 用户行为
     */
    void append(UserBehavior userBehavior);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userId
     * @param goodsId
     * @param behavior 用户行为
     */
    void append(long userId, long goodsId, int behavior);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userId
     * @param goodsId
     * @param behaviorEnum 用户行为枚举
     */
    void append(long userId, long goodsId, UserBehaviorEnum behaviorEnum);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param usrId
     * @param goodsId
     * @param preference 追加的偏好值
     */
    void appendByPreference(long usrId, long goodsId, int preference);

}
