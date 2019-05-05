package com.chenlinghong.graduation.scheduler.recommender.user.impl;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.recommender.user.UserTagBasedRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.scheduler.recommender.user.UserTagBasedRecommenderScheduler;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 基于用户标签推荐
 * @Author chenlinghong
 * @Date 2019/5/3 22:56
 * @Version V1.0
 */
@Slf4j
@Service(value = "userTagBasedRecommenderScheduler")
public class UserTagBasedRecommenderSchedulerImpl implements UserTagBasedRecommenderScheduler {

    @Autowired
    private RecommendQueueGoodsService recommendQueueGoodsService;

    @Autowired
    private UserTagBasedRecommender userTagBasedRecommender;

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
            queueGoods.setRecommendType(RecommendTypeEnum.USER_TAG_BASED_RECOMMEND.getCode());
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
    public Long refreshRecommendQueue(long userId) {
        /**
         * 更新推荐队列数据
         */
        int markResult = recommendQueueGoodsService.markRead(userId, RecommendTypeEnum.USER_TAG_BASED_RECOMMEND);
        /**
         * 获取推荐结束
         */
        RecommendDto recommendDto = userTagBasedRecommender.recommend(userId);
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
