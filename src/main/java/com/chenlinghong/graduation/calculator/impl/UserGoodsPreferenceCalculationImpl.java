package com.chenlinghong.graduation.calculator.impl;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.calculator.UserGoodsPreferenceCalculation;
import com.chenlinghong.graduation.microscope.sniffer.behavior.util.UserBehaviorUtil;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.repository.domain.UserPreference;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @Description 用户-商品偏好 计算实现类
 * @Author chenlinghong
 * @Date 2019/4/26 13:49
 * @Version V1.0
 */
@Service
public class UserGoodsPreferenceCalculationImpl implements UserGoodsPreferenceCalculation {

    @Override
    public Integer calculation(int behavior) {
        UserBehaviorEnum behaviorEnum = UserBehaviorUtil.getByBehavior(behavior);
        if (behaviorEnum != null) {
            return behaviorEnum.getFactor().intValue();
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
        return new UserPreference(userId, goodsId, calculation(behavior).intValue());
    }

    @Override
    public List<UserPreference> calculation(List<UserBehavior> behaviorList) {
        // 1、检查列表中是否存在非法数据：其它用户数据。若存在则直接返回空列表
        if (checkUser(behaviorList) == false) {
            return Lists.newArrayList();
        }
        // 2、按照商品ID对列表进行排序
        sortByGoodsId(behaviorList);
        // 3、抽离出每一个商品的用户行为信息，计算用户偏好
        // 4、组装列表，返回结果
        return calculationByOneUserAndSortedBehavior(behaviorList);
    }


    @Override
    public UserPreference calculationOne(List<UserBehavior> behaviorList) {
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
    public Integer calculationByBehavior(List<Integer> behaviorList) {
        int preference = 0;
        for (Integer item : behaviorList) {
            preference += calculation(item);
        }
        return preference;
    }

    @Override
    public List<UserPreference> calculationByOneUserAndSortedBehavior(List<UserBehavior> behaviorList) {
        if (behaviorList == null || behaviorList.size() <= 0) {
            return Lists.newArrayList();
        }
        List<UserPreference> result = Lists.newArrayList();
        // 获取userId
        long userId = behaviorList.get(0).getUserId();
        // 获取上一个GoodsId值
        long previousGoods = behaviorList.get(0).getGoodsId();
        // 获取临时偏好值
        int tempPreference = 0;
        /**
         * 遍历behavior列表，进行计算每一个Goods的偏好
         */
        for (UserBehavior behavior : behaviorList) {
            if (behavior.getGoodsId() != previousGoods) {
                // 说明遍历到新的goods,写入结果数据
                UserPreference userPreference = new UserPreference();
                userPreference.setUserId(userId);
                userPreference.setGoodsId(previousGoods);
                userPreference.setPreference(tempPreference);
                result.add(userPreference);
                // 更新当前值
                previousGoods = behavior.getGoodsId();
                tempPreference = calculation(behavior.getBehavior());
            } else {
                // 相同goods
                tempPreference += calculation(behavior.getBehavior());
            }
        }
        // 写入最后一个goods
        UserPreference lastPreference = new UserPreference(userId, previousGoods, tempPreference);
        result.add(lastPreference);
        return result;
    }

    /**
     * 校验用户的合法性，判断list中是否为同一个用户
     *
     * @param behaviorList
     * @return
     */
    private boolean checkUser(final List<UserBehavior> behaviorList) {
        if (behaviorList == null || behaviorList.size() <= 0) {
            /**
             * 此种情况根据该方法的逻辑，应该是属于正确的。但对其项目来讲，返回false方便后续工作
             */
            return false;
        }
        long userId = behaviorList.get(0).getUserId();
        for (UserBehavior item : behaviorList) {
            if (item.getUserId() != userId) {
                return false;
            }
        }
        return true;
    }


    /**
     * 按照商品ID排序
     *
     * @param behaviorList
     * @return
     */
    private List<UserBehavior> sortByGoodsId(List<UserBehavior> behaviorList) {
        Collections.sort(behaviorList, new Comparator<UserBehavior>() {
            @Override
            public int compare(UserBehavior o1, UserBehavior o2) {
                Collator collator = Collator.getInstance(Locale.CHINA);
                return collator.compare(o1.getGoodsId().toString(), o2.getGoodsId().toString());
            }
        });
        return behaviorList;
    }


}
