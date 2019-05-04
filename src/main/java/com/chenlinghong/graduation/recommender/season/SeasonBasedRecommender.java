package com.chenlinghong.graduation.recommender.season;

import com.chenlinghong.graduation.recommender.GraduationRecommender;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;

/**
 * @Description 时令推荐
 * @Author chenlinghong
 * @Date 2019/5/2 15:56
 * @Version V1.0
 */
public interface SeasonBasedRecommender extends GraduationRecommender {

    /**
     * 分页获取
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    RecommendDto<RecommendGoodsDto> recommend(final int pageNo, final int pageSize);


}
