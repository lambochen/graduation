package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.recommender.ranking.RankingGoodsRecommender;
import com.chenlinghong.graduation.recommender.season.SeasonBasedRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.chenlinghong.graduation.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
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
    public PageDto<RecommendRankingGoods> popularRecommend(long pageNo, long pageSize) {
        /**
         * 热门推荐
         */
        return rankingGoodsRecommender.range(pageNo, pageSize);
    }

    @Override
    public PageDto<RecommendQueueGoods> seasonRecommend(long pageNo, long pageSize) {
        /**
         * 时令推荐
         */
        RecommendDto<RecommendGoodsDto> seasonRecommendDto = seasonBasedRecommender.recommend(pageNo, pageSize);
        return recommendQueueGoodsService.converter(seasonRecommendDto);
    }

    @Override
    public PageDto<RecommendQueueGoods> recommendByType(RecommendTypeEnum typeEnum, long userId) throws TasteException {
        if (typeEnum == null || userId <= 0) {
            log.error("RecommendService#recommendByType: param is null. typeEnum={}, userId={}.", typeEnum, userId);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        } else if (typeEnum == RecommendTypeEnum.SEASON_RECOMMEND || typeEnum == RecommendTypeEnum.POPULAR_RECOMMEND) {
            log.error("RecommendService#recommendByType: param is illegal. typeEnum={}, userId={}.", typeEnum, userId);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return recommendQueueGoodsService.listByUserAndType(userId, typeEnum, NumericConstant.ONE, NumericConstant.TEN);
    }
}
