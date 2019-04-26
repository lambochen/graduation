package com.chenlinghong.graduation.microscope.actuator.impl;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.microscope.actuator.UserGoodsPreferenceActuator;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description 用户-商品偏好执行器实现类
 * @Author chenlinghong
 * @Date 2019/4/26 16:18
 * @Version V1.0
 */
@Slf4j
@Service
public class UserGoodsPreferenceActuatorImpl implements UserGoodsPreferenceActuator {


    @Override
    public void refresh(long userId, long goodsId) {

    }

    @Override
    public void refresh(long userId, long goodsId, Date startTime) {

    }

    @Override
    public void refresh(long userId, long goodsId, int preference) {

    }

    @Override
    public void append(UserBehavior userBehavior) {

    }

    @Override
    public void append(long userId, long goodsId, int behavior) {

    }

    @Override
    public void append(long userId, long goodsId, UserBehaviorEnum behaviorEnum) {

    }

    @Override
    public void appendByPreference(long usrId, long goodsId, int preference) {

    }
}
