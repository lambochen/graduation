package com.chenlinghong.graduation.scheduler.recommender.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.converter.RecommenderConverter;
import com.chenlinghong.graduation.recommender.cf.UserBasedCFRecommender;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.scheduler.recommender.UserBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class UserBasedCFRecommenderSchedulerImpl implements UserBasedCFRecommenderScheduler {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Autowired
    RecommenderConverter recommenderConverter;

    /**
     * 基于用户推荐器
     */
    private UserBasedCFRecommender recommender;

    /**
     * 邻居数
     */
    private static final int neighborhoodNumber = NumericConstant.TWENTY;

    @PostConstruct
    private void init() throws TasteException {
        recommender = new UserBasedCFRecommender(dataSource, neighborhoodNumber);
    }

    @Override
    public RecommendDto recommend(final long userId) throws TasteException {
        /**
         * 默认推荐10条数据
         */
        return recommend(userId, NumericConstant.TEN);
    }

    @Override
    public RecommendDto recommend(final long userId, final int recommendNum) throws TasteException {
        if (userId <= 0 || recommendNum <= 0) {
            log.error("UserBasedCFRecommenderScheduler#recommend: param is illegal. userId={}, " +
                    "recommendNum={}.", userId, recommendNum);
            return null;
        }
        RecommendDto<RecommendGoodsDto> result = new RecommendDto<>();
        result.setUserId(userId);
        /**
         * 填充User
         */
        User user = getUser(userId);
        result.setUser(user);
        /**
         * 获取推荐结果
         */
        PageDto data = recommendByUserCF(userId, recommendNum);
        result.setData(data);
        return result;
    }

    /**
     * 获取推荐结果
     *
     * @param userId
     * @return
     */
    private PageDto<RecommendGoodsDto> recommendByUserCF(final long userId, final int recommendNum) throws TasteException {
        /**
         * 获取推荐结果
         */
        List<RecommendedItem> recommendData = recommender.recommend(userId, recommendNum);
        if (recommendData == null || recommendData.size() <= 0) {
            log.error("UserBasedCFRecommenderScheduler#recommendByUserCF: failed to get recommend data. userId={}, " +
                    "recommendNum={}, recommendData={}.", userId, recommendNum, recommendData);
            return null;
        }
        /**
         * 转换数据
         */
        List<RecommendGoodsDto> goodsList = recommenderConverter.convert(recommendData);
        PageDto<RecommendGoodsDto> result = new PageDto<>(goodsList);
        return result;
    }

    /**
     * 获取User
     *
     * @param userId
     * @return
     */
    private User getUser(long userId) {
        /**
         * TODO 从缓存中读取User
         */
        return null;
    }
}
