package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;

/**
 * @Description 推荐Service
 * @Author chenlinghong
 * @Date 2019/5/4 11:12
 * @Version V1.0
 */
public interface RecommendService {

    /**
     * 热门推荐
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommendRankingGoods> popularRecommend(int pageNo, int pageSize);

    /**
     * 时令推荐
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommendQueueGoods> seasonRecommend(int pageNo, int pageSize);

}
