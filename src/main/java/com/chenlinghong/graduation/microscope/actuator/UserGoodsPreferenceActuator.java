package com.chenlinghong.graduation.microscope.actuator;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

/**
 * @Description 用户偏好执行器
 * @Author chenlinghong
 * @Date 2019/4/26 15:19
 * @Version V1.0
 */
@Async(value = AsyncNameConstant.MICROSCOPE)
public interface UserGoodsPreferenceActuator extends PreferenceActuator {

    /**
     * 刷新用户偏好
     *
     * @param userPreference
     */
    Boolean refresh(UserPreference userPreference);

    /**
     * 刷新用户偏好，所有商品均刷新，采用默认时间窗口
     *
     * @param userId
     */
    Boolean refresh(long userId);

    /**
     * 刷新用户偏好，所有商品均刷新
     *
     *          * 1、获取用户指定时间窗口的所有行为
     *          * 2、分不同的商品进行计算每个商品的偏好
     *          * 3、将商品偏好分为两大类，已存在用户偏好记录和尚不存在
     *          * 4、对已存在的用户偏好记录，执行批量更新操作
     *          * 5、对不存在的用户偏好记录，执行批量插入操作
     *          *
     *          * 注：该方式所需资源较多，谨慎使用
     *
     * @param userId
     * @param startTime
     */
    Boolean refresh(long userId, Date startTime);

    /**
     * 刷新用户偏好，采用默认时间窗口
     *
     * @param userId  用户ID
     * @param goodsId 商品ID
     */
    Boolean refresh(long userId, long goodsId);

    /**
     * 刷新用户偏好，抓取数据startTime
     *
     * @param userId    用户ID
     * @param goodsId   商户ID
     * @param startTime 开始时间
     */
    Boolean refresh(long userId, long goodsId, Date startTime);

    /**
     * 刷新用户偏好
     *
     * @param userId
     * @param goodsId
     * @param preference 用户偏好新值
     */
    Boolean refresh(long userId, long goodsId, int preference);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userBehavior 用户行为
     */
    Boolean append(UserBehavior userBehavior);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userId
     * @param goodsId
     * @param behavior 用户行为
     */
    Boolean append(long userId, long goodsId, int behavior);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param userId
     * @param goodsId
     * @param behaviorEnum 用户行为枚举
     */
    Boolean append(long userId, long goodsId, UserBehaviorEnum behaviorEnum);

    /**
     * 刷新用户偏好，以追加方式
     *
     * @param usrId
     * @param goodsId
     * @param preference 追加的偏好值
     */
    Boolean appendByPreference(long usrId, long goodsId, int preference);

}
