package com.chenlinghong.graduation.recommender;

import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;

/**
 * @Description 推荐器
 * @Author chenlinghong
 * @Date 2019/4/27 17:45
 * @Version V1.0
 */
public interface GraduationRecommender extends Recommender {

    /**
     * 推荐默认条数
     *
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

    /**
     * 推荐默认条数
     *
     * @param userId 用户ID
     * @return
     */
    RecommendDto<RecommendGoodsDto> recommend(final long userId);

    /**
     * 推荐指定条数
     *
     * @param userId       用户ID
     * @param recommendNum 推荐条数
     * @return
     */
    RecommendDto<RecommendGoodsDto> recommend(final long userId, final int recommendNum);


}
