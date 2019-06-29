package com.chenlinghong.graduation.scheduler.recommender.cf.impl;

import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.recommender.cf.UserBasedCFRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.scheduler.recommender.AbstractMahoutRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * @Description 基于用户的协同过滤推荐执行器实现类
 * @Author chenlinghong
 * @Date 2019/4/30 22:32
 * @Version V1.0
 */
@Slf4j
@Service(value = "userBasedCFRecommenderScheduler")
public class UserBasedCFRecommenderSchedulerImpl
        extends AbstractMahoutRecommenderScheduler implements UserBasedCFRecommenderScheduler {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RecommendQueueGoodsService recommendQueueGoodsService;

    /**
     * 邻居数
     */
    private static final int neighborhoodNumber = NumericConstant.TEN;

    @PostConstruct
    public void init() throws TasteException {
        recommender = new UserBasedCFRecommender(dataSource, neighborhoodNumber);
    }

    @Override
    @Async(value = AsyncNameConstant.SCHEDULER)
    public Long refreshRecommendQueue(long userId) throws TasteException {
        if (recommender == null) {
            synchronized (UserBasedCFRecommenderSchedulerImpl.class) {
                if (recommender == null) {
                    recommender = new UserBasedCFRecommender(dataSource, neighborhoodNumber);
                }
            }
        }
        /**
         * 更新推荐队列数据
         */
        int markResult = recommendQueueGoodsService.markRead(userId, RecommendTypeEnum.USER_BASED_RECOMMEND);
        /**
         * 获取推荐结束
         */
        RecommendDto recommendDto = recommend(userId);
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
            queueGoods.setRecommendType(RecommendTypeEnum.USER_BASED_RECOMMEND.getCode());
            queueGoods.setUserId(recommendDto.getUserId());
            if (goodsDto.getGoods() == null) {
                continue;
            }
            queueGoods.setGoodsId(goodsDto.getGoods().getId());
            result.add(queueGoods);
        }
        return result;
    }

}
