package com.chenlinghong.graduation.recommender.season;

import com.chenlinghong.graduation.recommender.Recommender;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;

/**
 * @Description 时令推荐
 * @Author chenlinghong
 * @Date 2019/5/2 15:56
 * @Version V1.0
 */
public interface SeasonBasedRecommender extends Recommender {

    /**
     * 推荐默认条数
     * @return
     */
    RecommendDto<RecommendGoodsDto> recommend();

    /**
     * 推荐指定条数
     *
     * @param recommendNum 推荐条数
     * @return
     */
    RecommendDto<RecommendGoodsDto> recommend(final int recommendNum);

}
