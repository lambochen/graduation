package com.chenlinghong.graduation.recommender.user.impl;

import com.chenlinghong.graduation.recommender.AbstractGraduationRecommender;
import com.chenlinghong.graduation.recommender.user.UserTagBasedRecommender;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description 基于用户标签推荐
 * @Author chenlinghong
 * @Date 2019/5/2 17:57
 * @Version V1.0
 */
@Slf4j
@Service
public class UserTagBasedRecommenderImpl extends AbstractGraduationRecommender implements UserTagBasedRecommender {



    @Override
    public RecommendDto<RecommendGoodsDto> recommend() {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(int recommendNum) {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId) {
        return null;
    }

    @Override
    public RecommendDto<RecommendGoodsDto> recommend(long userId, int recommendNum) {
        return null;
    }
}
