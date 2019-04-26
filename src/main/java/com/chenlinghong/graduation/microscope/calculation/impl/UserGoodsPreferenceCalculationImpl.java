package com.chenlinghong.graduation.microscope.calculation.impl;

import com.chenlinghong.graduation.microscope.calculation.UserGoodsPreferenceCalculation;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.microscope.sniffer.util.UserBehaviorUtil;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户-商品偏好 计算实现类
 * @Author chenlinghong
 * @Date 2019/4/26 13:49
 * @Version V1.0
 */
@Service
public class UserGoodsPreferenceCalculationImpl implements UserGoodsPreferenceCalculation {

    @Override
    public int calculation(int behavior) {
        UserBehaviorEnum behaviorEnum = UserBehaviorUtil.getByBehavior(behavior);
        if (behaviorEnum != null) {
            return behaviorEnum.getFactor();
        }
        return 0;
    }

    @Override
    public UserPreference calculation(UserBehavior userBehavior) {
        long userId = userBehavior.getUserId();
        long goodsId = userBehavior.getGoodsId();
        int preference = calculation(userBehavior.getBehavior());
        return new UserPreference(userId, goodsId, preference);
    }

    @Override
    public UserPreference calculation(long userId, long goodsId, int behavior) {
        return new UserPreference(userId, goodsId, calculation(behavior));
    }

    @Override
    public UserPreference calculation(List<UserBehavior> behaviorList) {
        if (behaviorList == null || behaviorList.size() <= 0) {
            return null;
        }
        UserPreference preference = new UserPreference();
        /**
         * 获取第一个对象
         */
        UserBehavior behavior = behaviorList.get(0);
        preference.setUserId(behavior.getUserId());
        preference.setGoodsId(behavior.getGoodsId());

        int preferenceValue = 0;
        for (UserBehavior item : behaviorList) {
            preferenceValue += calculation(item.getBehavior());
        }
        preference.setPreference(preferenceValue);
        return preference;
    }

    @Override
    public int calculationByBehavior(List<Integer> behaviorList) {
        int preference = 0;
        for (Integer item : behaviorList) {
            preference += calculation(item);
        }
        return preference;
    }

}
