package com.chenlinghong.graduation.microscope.sniffer.behavior.util;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;

/**
 * @Description 用户行为工具类
 * @Author chenlinghong
 * @Date 2019/4/25 23:11
 * @Version V1.0
 */
public class UserBehaviorUtil {


    /**
     * 通过评分获取
     *
     * @param score
     * @return
     */
    public static UserBehaviorEnum getByCommentScore(int score) {
        if (score == NumericConstant.ONE) {
            return UserBehaviorEnum.COMMENT_SCORE_1;
        } else if (score == NumericConstant.TWO) {
            return UserBehaviorEnum.COMMENT_SCORE_2;
        } else if (score == NumericConstant.THREE) {
            return UserBehaviorEnum.COMMENT_SCORE_3;
        } else if (score == NumericConstant.FOUR) {
            return UserBehaviorEnum.COMMENT_SCORE_4;
        } else if (score == NumericConstant.FIVE) {
            return UserBehaviorEnum.COMMENT_SCORE_5;
        }
        return null;
    }


    /**
     * 根据用户行为获取
     *
     * @param behavior
     * @return
     */
    public static UserBehaviorEnum getByBehavior(int behavior) {
        UserBehaviorEnum result = null;
        switch (behavior) {
            case NumericConstant.ONE:
                result = UserBehaviorEnum.CLICK;
                break;
            case NumericConstant.TWO:
                result = UserBehaviorEnum.ADD_TO_CART;
                break;
            case NumericConstant.THREE:
                result = UserBehaviorEnum.PURCHASE;
                break;
            case NumericConstant.FOUR:
                result = UserBehaviorEnum.COMMENT_SCORE_1;
                break;
            case NumericConstant.FIVE:
                result = UserBehaviorEnum.COMMENT_SCORE_2;
                break;
            case NumericConstant.SIX:
                result = UserBehaviorEnum.COMMENT_SCORE_3;
                break;
            case NumericConstant.SEVEN:
                result = UserBehaviorEnum.COMMENT_SCORE_4;
                break;
            case NumericConstant.EITHT:
                result = UserBehaviorEnum.COMMENT_SCORE_5;
                break;
            case NumericConstant.NINE:
                result = UserBehaviorEnum.SEARCH;
                break;
            case NumericConstant.TEN:
                result = UserBehaviorEnum.PATTERN_RECOGNITION;
                break;

        }
        return result;
    }

}
