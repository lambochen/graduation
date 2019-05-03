package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.recommender.ranking.RankingGoodsRecommender;
import com.chenlinghong.graduation.repository.dao.RecommendQueueGoodsDao;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.scheduler.recommender.cf.ItemBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.SlopeOneCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.cf.UserBasedCFRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.season.SeasonBasedRecommenderScheduler;
import com.chenlinghong.graduation.scheduler.recommender.user.UserTagBasedRecommenderScheduler;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.chenlinghong.graduation.service.dto.RecommendQueueGoodsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品推荐队列, 可异步执行
 * @Author chenlinghong
 * @Date 2019/5/3 11:02
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommendQueueGoodsServiceImpl implements RecommendQueueGoodsService {

    @Autowired
    private RecommendQueueGoodsDao recommendQueueGoodsDao;

    /**
     * 热门推荐
     */
    @Autowired
    private RankingGoodsRecommender rankingGoodsRecommender;

    /**
     * 基于用户的协同过滤推荐执行器
     */
    @Autowired
    private UserBasedCFRecommenderScheduler userBasedCFRecommenderScheduler;

    /**
     * 基于物品的协同过滤推荐执行器
     */
    @Autowired
    private ItemBasedCFRecommenderScheduler itemBasedCFRecommenderScheduler;

    /**
     * SlopeOne协同过滤推荐执行器
     */
    @Autowired
    private SlopeOneCFRecommenderScheduler slopeOneCFRecommenderScheduler;

    /**
     * 时令推荐
     */
    @Autowired
    private SeasonBasedRecommenderScheduler seasonBasedRecommenderScheduler;

    /**
     * 基于用户标签推荐
     */
    @Autowired
    private UserTagBasedRecommenderScheduler userTagBasedRecommenderScheduler;


    @Override
    public int insert(RecommendQueueGoods recommendQueueGoods) {
        if (recommendQueueGoods == null) {
            log.error("RecommendQueueGoodsService#insert: param is null.");
            return 0;
        }
        return recommendQueueGoodsDao.insert(recommendQueueGoods);
    }

    @Override
    public int insert(List<RecommendQueueGoods> recommendQueueGoodsList) {
        if (recommendQueueGoodsList == null || recommendQueueGoodsList.size() <= 0) {
            log.error("RecommendQueueGoodsService#insert: param is null.");
            return 0;
        }
        int result = recommendQueueGoodsDao.insertBatch(recommendQueueGoodsList);
        /**
         * TODO 校验结果
         */
        return result;
    }

    @Override
    public int deleteById(long id) {
        if (id <= 0) {
            log.error("RecommendQueueGoodsService#deleteById: param is illegal. id={}.", id);
            return 0;
        }
        return recommendQueueGoodsDao.deleteById(id);
    }

    @Override
    public RecommendQueueGoods getById(long id) {
        if (id <= 0) {
            log.error("RecommendQueueGoodsService#getById: param is illegal. id={}.", id);
            return null;
        }
        return null;
    }

    @Override
    public PageDto<RecommendQueueGoods> listAll(long pageNo, long pageSize) {
        if (pageNo <= 0 || pageSize < 0) {
            log.error("RecommendRankingGoodsService#listAll: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return new PageDto<>();
        }
        List<RecommendQueueGoods> goodsList = recommendQueueGoodsDao.listAll((pageNo - 1) * pageSize, pageSize);
        int total = recommendQueueGoodsDao.count();
        return new PageDto<>(goodsList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<RecommendQueueGoods> listByUserAndType(long userId, RecommendTypeEnum typeEnum,
                                                          int pageNo, int pageSize) throws TasteException {
        List<RecommendQueueGoods> data = recommendQueueGoodsDao.listByUserAndType(userId, typeEnum.getCode(),
                (pageNo - 1) * pageSize, pageSize);
        int total = recommendQueueGoodsDao.countByUserAndType(userId, typeEnum.getCode());
        refreshRecommendQueue(userId, typeEnum);
        return new PageDto<>(data, pageNo, pageSize, total);
    }

    @Override
    public PageDto<RecommendQueueGoods> listByUserAndType(long userId, RecommendTypeEnum typeEnum)
            throws TasteException {
        return listByUserAndType(userId, typeEnum, NumericConstant.ONE, NumericConstant.THREE);
    }

    @Override
    public RecommendQueueGoodsDto listByUser(long userId) throws TasteException {
        /**
         * 获取数据后，需要重新进行推荐写入
         */
        RecommendQueueGoodsDto result = new RecommendQueueGoodsDto();
        /**
         * 基于用户的协同过滤推荐
         */
        PageDto<RecommendQueueGoods> userBasedRecommend =
                listByUserAndType(userId, RecommendTypeEnum.USER_BASED_RECOMMEND);
        result.setUserBasedRecommend(userBasedRecommend);
        /**
         * 基于物品推荐
         */
        PageDto<RecommendQueueGoods> itemBasedRecommend =
                listByUserAndType(userId, RecommendTypeEnum.ITEM_BASED_RECOMMEND);
        result.setItemBasedRecommend(itemBasedRecommend);
        /**
         * SlopeOne推荐，基于评分推荐
         */
        PageDto<RecommendQueueGoods> slopeOneRecommend =
                listByUserAndType(userId, RecommendTypeEnum.SLOPE_ONE_RECOMMEND);
        result.setSlopeOneRecommend(slopeOneRecommend);
        /**
         * 热门推荐
         */
        PageDto<RecommendRankingGoods> popularRecommend = rankingGoodsRecommender.topN(NumericConstant.THREE);
        result.setPopularRecommend(popularRecommend);
        /**
         * 时令推荐
         */
        PageDto<RecommendQueueGoods> seasonRecommend = listByUserAndType(userId, RecommendTypeEnum.SEASON_RECOMMEND);
        result.setSeasonRecommend(seasonRecommend);
        /**
         * 基于用户标签的推荐
         */
        PageDto<RecommendQueueGoods> userTagBasedRecommend =
                listByUserAndType(userId, RecommendTypeEnum.USER_TAG_BASED_RECOMMEND);
        result.setUserTagBasedRecommend(userTagBasedRecommend);
        return result;
    }

    @Override
    public int markRead(long userId, RecommendTypeEnum userBasedRecommend) {
        return recommendQueueGoodsDao.markRead(userId, userBasedRecommend.getCode());
    }

    @Override
    public int update(RecommendQueueGoods recommendQueueGoods) {
        if (recommendQueueGoods == null) {
            log.error("RecommendQueueGoodsService#update: param is null.");
            return 0;
        }
        return recommendQueueGoodsDao.update(recommendQueueGoods);
    }

    /**
     * 刷新推荐队列
     *
     * @param userId   用户ID
     * @param typeEnum 推荐类型
     * @return 新写入数据数目
     */
    private Long refreshRecommendQueue(long userId, RecommendTypeEnum typeEnum) throws TasteException {
        long result = 0;
        switch (typeEnum) {
            /**
             * 基于用户的协同过滤推荐
             */
            case USER_BASED_RECOMMEND:
                result = userBasedCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 基于物品推荐
             */
            case ITEM_BASED_RECOMMEND:
                result = itemBasedCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * SlopeOne推荐，基于评分推荐
             */
            case SLOPE_ONE_RECOMMEND:
                result = slopeOneCFRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 时令推荐
             */
            case SEASON_RECOMMEND:
                result = seasonBasedRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 基于用户标签的推荐
             */
            case USER_TAG_BASED_RECOMMEND:
                result = userTagBasedRecommenderScheduler.refreshRecommendQueue(userId);
                break;
            /**
             * 热门推荐
             */
            case POPULAR_RECOMMEND:
                break;
        }
        return result;
    }


}
