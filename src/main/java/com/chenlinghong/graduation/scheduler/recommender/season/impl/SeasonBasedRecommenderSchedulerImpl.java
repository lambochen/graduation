package com.chenlinghong.graduation.scheduler.recommender.season.impl;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.recommender.season.SeasonBasedRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.scheduler.recommender.season.SeasonBasedRecommenderScheduler;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 时令推荐
 * @Author chenlinghong
 * @Date 2019/5/3 22:43
 * @Version V1.0
 */
@Slf4j
@Service(value = "seasonBasedRecommenderSchedulerImpl")
public class SeasonBasedRecommenderSchedulerImpl implements SeasonBasedRecommenderScheduler {

    @Autowired
    private RecommendQueueGoodsService recommendQueueGoodsService;

    @Autowired
    private SeasonBasedRecommender seasonBasedRecommender;

    @Override
    public RecommendDto recommend(long userId) throws TasteException {
        return null;
    }

    @Override
    public RecommendDto recommend(long userId, int recommendNum) throws TasteException {
        return null;
    }

    @Override
    public List<RecommendQueueGoods> converter(RecommendDto<RecommendGoodsDto> recommendDto) {
        List<RecommendQueueGoods> result = Lists.newArrayList();
        if (recommendDto == null
                || recommendDto.getData() == null
                || recommendDto.getData().getData() == null
                || recommendDto.getData().getData().size() <= 0) {
            return result;
        }
        for (RecommendGoodsDto goodsDto : recommendDto.getData().getData()) {
            RecommendQueueGoods queueGoods = new RecommendQueueGoods();
            queueGoods.setRecommendType(RecommendTypeEnum.SEASON_RECOMMEND.getCode());
            queueGoods.setUserId(recommendDto.getUserId());
            if (goodsDto.getGoods() == null) {
                continue;
            }
            queueGoods.setGoodsId(goodsDto.getGoods().getId());
            result.add(queueGoods);
        }
        return result;
    }

    @Override
    @Async(value = AsyncNameConstant.SCHEDULER)
    public Long refreshRecommendQueue(long userId) throws TasteException {
        /**
         * 更新推荐队列数据
         */
        int markResult = recommendQueueGoodsService.markRead(userId, RecommendTypeEnum.SEASON_RECOMMEND);
        /**
         * 获取推荐结束
         */
        RecommendDto recommendDto = seasonBasedRecommender.recommend(userId);

        /**
         * 转换推荐队列数据
         */
        List<RecommendQueueGoods> recommendQueueGoodsList = converter(recommendDto);
        /**
         * 写入推荐队列
         */
        int dataResult = recommendQueueGoodsService.insert(recommendQueueGoodsList);
        /**
         * TODO 处理结果
         */
        return (long) dataResult;
    }
}
