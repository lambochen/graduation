package com.chenlinghong.graduation.microscope.sniffer.util;

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

}
