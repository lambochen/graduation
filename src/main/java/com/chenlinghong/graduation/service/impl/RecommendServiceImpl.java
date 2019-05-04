package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.recommender.ranking.RankingGoodsRecommender;
import com.chenlinghong.graduation.recommender.season.SeasonBasedRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.chenlinghong.graduation.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 推荐
 * @Author chenlinghong
 * @Date 2019/5/4 11:14
 * @Version V1.0
 */
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    /**
     * 热门推荐
     */
    @Autowired
    private RankingGoodsRecommender rankingGoodsRecommender;

    /**
     * 时令推荐
     */
    @Autowired
    private SeasonBasedRecommender seasonBasedRecommender;

    /**
     * 推荐队列
     */
    @Autowired
    private RecommendQueueGoodsService recommendQueueGoodsService;

    @Override
    public PageDto<RecommendRankingGoods> popularRecommend(int pageNo, int pageSize) {
        /**
         * 热门推荐
         */
        return rankingGoodsRecommender.range(pageNo,pageSize);
    }

    @Override
    public PageDto<RecommendQueueGoods> seasonRecommend(int pageNo, int pageSize) {
        /**
         * 时令推荐
         */
        RecommendDto<RecommendGoodsDto> seasonRecommendDto = seasonBasedRecommender.recommend(pageNo, pageSize);
        return recommendQueueGoodsService.converter(seasonRecommendDto);
    }
}
