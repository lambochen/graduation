package com.chenlinghong.graduation.microscope.actuator.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.microscope.actuator.UserGoodsPreferenceActuator;
import com.chenlinghong.graduation.calculator.UserGoodsPreferenceCalculation;
import com.chenlinghong.graduation.microscope.sniffer.behavior.util.UserBehaviorUtil;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;
import com.chenlinghong.graduation.service.UserBehaviorService;
import com.chenlinghong.graduation.service.UserPreferenceService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户-商品偏好执行器实现类
 * @Author chenlinghong
 * @Date 2019/4/26 16:18
 * @Version V1.0
 */
@Slf4j
@Service
public class UserGoodsPreferenceActuatorImpl implements UserGoodsPreferenceActuator {

    @Autowired
    private UserPreferenceService preferenceService;

    @Autowired
    private UserBehaviorService behaviorService;

    @Autowired
    private UserGoodsPreferenceCalculation preferenceCalculation;

    @Override
    public Boolean refresh(UserPreference userPreference) {
        if (userPreference == null) {
            log.error("UserGoodsPreferenceActuator#refresh: param is null.");
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = 0;
        if (preferenceService.isNotAliveUserPreference(userPreference)) {
            /**
             * 新增偏好信息
             */
            result = preferenceService.insert(userPreference);
        } else {
            result = preferenceService.update(userPreference);
        }

        if (result == NumericConstant.ONE) {
            return true;
        }
        /**
         * TODO 处理异常
         */
        return false;
    }

    @Override
    public Boolean refresh(long userId) {
        Date startTime = getDefaultStartTime();
        return refresh(userId, startTime);
    }


    @Override
    public Boolean refresh(long userId, Date startTime) {
        /**
         * 1、获取所有用户行为
         */
        PageDto behaviorDto = behaviorService.listByUserAndStartTime(userId, startTime);
        if (behaviorDto == null || behaviorDto.getTotalCount() <= 0) {
            // 为获取到数据
            log.error("UserGoodsPreferenceActuator#refresh: no data. userId={}, startTime={}.", userId, startTime);
            throw new AsyncBusinessException(ErrorEnum.BEHAVIOR_DATA_NOT_EXISTS);
        }
        /**
         * 2、计算用户对于每个商品的偏好
         */
        List<UserPreference> preferenceList = preferenceCalculation.calculation(behaviorDto.getData());
        /**
         * 3、将商品偏好分为两大类，已存在用户偏好记录和尚不存在
         */
        List<UserPreference> alivePreferenceList = Lists.newArrayList();
        List<UserPreference> notAlivePreferenceList = Lists.newArrayList();
        for (UserPreference item : preferenceList) {
            if (preferenceService.isAliveUserPreference(item)) {
                alivePreferenceList.add(item);
            } else {
                notAlivePreferenceList.add(item);
            }
        }
        /**
         * 4、对已存在的用户偏好记录，执行批量更新操作
         */
        if (alivePreferenceList.size() > 0) {
            int updateResult = preferenceService.update(alivePreferenceList);
            /**
             * TODO 校验结果
             */
        }
        /**
         * 5、对不存在的用户偏好记录，执行批量插入操作
         */
        if (notAlivePreferenceList.size() > 0) {
            int insertResult = preferenceService.insert(notAlivePreferenceList);
            /**
             * TODO 校验结果
             */
        }
        /**
         * TODO 处理结果
         */
        return true;
    }


    @Override
    public Boolean refresh(long userId, long goodsId) {
        Date startTime = getDefaultStartTime();
        return refresh(userId, goodsId, startTime);
    }

    @Override
    public Boolean refresh(long userId, long goodsId, Date startTime) {
        PageDto behaviorDto = behaviorService.listByUserAndGoodsAndStartTime(userId, goodsId, startTime);
        if (behaviorDto.getTotalCount() <= 0) {
            // 为获取到数据
            log.error("UserGoodsPreferenceActuator#refresh: no data. userId={}, goodsId={}, " +
                    "startTime={}. ", userId, goodsId, startTime);
            throw new AsyncBusinessException(ErrorEnum.BEHAVIOR_DATA_NOT_EXISTS);
        }
        int preference = 0;
        List<UserBehavior> behaviorList = behaviorDto.getData();
        for (UserBehavior behavior : behaviorList) {
            preference += preferenceCalculation.calculation(behavior.getBehavior());
        }
        return refresh(userId, goodsId, preference);
    }

    @Override
    public Boolean refresh(long userId, long goodsId, int preference) {
        UserPreference userPreference = new UserPreference(userId, goodsId, preference);
        return refresh(userPreference);
    }

    @Override
    public Boolean append(UserBehavior userBehavior) {
        if (userBehavior == null) {
            log.error("UserGoodsPreferenceActuator#append: param is null.");
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int preference = preferenceCalculation.calculation(userBehavior.getBehavior());
        return appendByPreference(userBehavior.getUserId().longValue(), userBehavior.getGoodsId().longValue(), preference);
    }

    @Override
    public Boolean append(long userId, long goodsId, int behavior) {
        UserBehaviorEnum behaviorEnum = UserBehaviorUtil.getByBehavior(behavior);
        return append(userId, goodsId, behaviorEnum);
    }

    @Override
    public Boolean append(long userId, long goodsId, UserBehaviorEnum behaviorEnum) {
        if (userId <= 0 || goodsId <= 0 || behaviorEnum == null) {
            log.error("UserGoodsPreferenceActuator#append: param is illegal. userId={}, goodsId={}, " +
                    "behaviorEnum={}. ", userId, goodsId, behaviorEnum);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return appendByPreference(userId, goodsId, behaviorEnum.getFactor());
    }

    @Override
    public Boolean appendByPreference(long usrId, long goodsId, int preference) {
        if (usrId <= 0 || goodsId <= 0 || preference < 0) {
            log.error("UserGoodsPreferenceActuator#appendByPreference: param is illegal. userId={}, goodsId={}," +
                    " preference={}. ", usrId, goodsId, preference);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        UserPreference userPreference = preferenceService.getByUserAndGoods(usrId, goodsId);
        if (userPreference == null) {
            log.error("UserGoodsPreferenceActuator#appendByPreference: user preference is not exists. userId={}, " +
                    "goodsId={}, preference={}. ", usrId, goodsId, preference);
            // throw new AsyncBusinessException(ErrorEnum.USER_PREFERENCE_NOT_EXISTS);
            /**
             * 偏好信息不存在，进行新建
             */
            userPreference = new UserPreference();
            userPreference.setUserId(usrId);
            userPreference.setGoodsId(goodsId);
        }
        preference += userPreference.getPreference() == null ? 0 : userPreference.getPreference();
        userPreference.setPreference(preference);
        return refresh(userPreference);
    }

    /**
     * 获取默认时间窗口，三个月
     *
     * @return
     */
    private synchronized Date getDefaultStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 设置为三个月之前的时间
        calendar.add(Calendar.MONTH, NumericConstant.THREE * -1);
        return calendar.getTime();
    }
}
