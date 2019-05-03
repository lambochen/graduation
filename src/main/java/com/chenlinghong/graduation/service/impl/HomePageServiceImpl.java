package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.api.vo.HomePageVo;
import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.CheckUserTypeEnum;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.HomePageService;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import com.chenlinghong.graduation.service.dto.RecommendQueueGoodsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 主页Service实现类
 * @Author chenlinghong
 * @Date 2019/4/29 9:26
 * @Version V1.0
 */
@Slf4j
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private GoodsCatalogService goodsCatalogService;

    @Autowired
    private RecommendQueueGoodsService recommendQueueGoodsService;

    // /**
    //  * 基于用户的协同过滤推荐
    //  */
    // @Autowired
    // private UserBasedCFRecommenderScheduler userBasedCFRecommenderScheduler;
    //
    // /**
    //  * 基于物品的协同过滤推荐
    //  */
    // @Autowired
    // private ItemBasedCFRecommenderScheduler itemBasedCFRecommenderScheduler;
    //
    // /**
    //  * SlopeOne推荐
    //  */
    // @Autowired
    // private SlopeOneCFRecommenderScheduler slopeOneCFRecommenderScheduler;
    //
    // /**
    //  * 时令推荐
    //  */
    // @Autowired
    // private SeasonBasedRecommender seasonBasedRecommender;
    //
    // /**
    //  * 基于用户标签推荐
    //  */
    // @Autowired
    // private UserTagBasedRecommender userTagBasedRecommender;
    //
    // /**
    //  * 热门推荐，商品ranking
    //  */
    // @Autowired
    // private RankingGoodsRecommender rankingGoodsRecommender;

    // @Override
    // public HomePageVo get(long userId) throws TasteException {
    //     HomePageVo result = new HomePageVo();
    //     /**
    //      * 获取目录信息
    //      */
    //     PageDto<GoodsCatalogOne> catalogPageDto =
    //             goodsCatalogService.listAll(NumericConstant.ONE, NumericConstant.ONE_HUNDRED);
    //     result.setCatalogPageDto(catalogPageDto);
    //
    //     /**
    //      * 校验userId，
    //      * 1、如果是有效老用户，需要进行个性化推荐。
    //      * 2、如果是有效新用户，则进行冷启动方案
    //      * 3、如果是非登录用户，则进行随机推荐(待确定方案)
    //      */
    //     CheckUserTypeEnum userCheckType = checkUser(userId);
    //     if (userCheckType == CheckUserTypeEnum.NEW_USER) {
    //         // 新用户
    //         /**
    //          * TODO 冷启动问题
    //          */
    //         /**
    //          * 基于用户标签推荐
    //          */
    //         RecommendDto userTagBasedRecommendDto = userTagBasedRecommender.recommend(userId, NumericConstant.THREE);
    //         result.setUserTagBasedRecommend(userTagBasedRecommendDto);
    //     }
    //     if (userCheckType == CheckUserTypeEnum.OLD_USER) {
    //         /**
    //          * 老用户
    //          *
    //          * 1、基于用户
    //          * 2、基于物品
    //          * 3、SlopeOne
    //          */
    //         /**
    //          * 基于用户的协同过滤推荐，推荐3条
    //          */
    //         RecommendDto userBasedRecommendDto =
    //                 userBasedCFRecommenderScheduler.recommend(userId, NumericConstant.THREE);
    //         result.setUserBasedRecommend(userBasedRecommendDto);
    //         /**
    //          * 基于物品的协同过滤推荐
    //          */
    //         RecommendDto itemBasedRecommendDto =
    //                 itemBasedCFRecommenderScheduler.recommend(userId, NumericConstant.THREE);
    //         result.setItemBasedRecommend(itemBasedRecommendDto);
    //         /**
    //          * SlopeOne推荐
    //          */
    //         RecommendDto slopeOneCFRecommendDto =
    //                 slopeOneCFRecommenderScheduler.recommend(userId, NumericConstant.THREE);
    //         result.setSlopeOneRecommend(slopeOneCFRecommendDto);
    //     }
    //
    //     // 非登录用户
    //     /**
    //      * TODO 非登录用户推荐
    //      * 1、热门推荐、时令推荐
    //      * 2、聚类推荐
    //      * 3、拟用户推荐、随机推荐
    //      */
    //     /**
    //      * 时令推荐
    //      * TODO 需要校验结果，可能会出现方法调用问题
    //      */
    //     RecommendDto seasonRecommendDto = seasonBasedRecommender.recommend(NumericConstant.THREE);
    //     result.setSeasonRecommend(seasonRecommendDto);
    //     /**
    //      * 热门推荐
    //      */
    //     PageDto<RecommendRankingGoods> rankingGoodsPageDto = rankingGoodsRecommender.topN(NumericConstant.THREE);
    //     result.setPopularRecommend(rankingGoodsPageDto);
    //
    //     return result;
    // }

    @Override
    public HomePageVo get(long userId) throws TasteException {
        HomePageVo result = new HomePageVo();
        /**
         * 获取目录信息
         */
        PageDto<GoodsCatalogOne> catalogPageDto =
                goodsCatalogService.listAll(NumericConstant.ONE, NumericConstant.ONE_HUNDRED);
        result.setCatalogPageDto(catalogPageDto);
        /**
         * 获取推荐数据，在推荐队列中获取
         */
        RecommendQueueGoodsDto recommendQueueGoodsDto = recommendQueueGoodsService.listByUser(userId);
        result.setRecommendQueueGoodsDto(recommendQueueGoodsDto);
        return result;
    }

    /**
     * 校验用户
     *
     * @param userId 用户ID
     * @return 0:非用户  1：新用户 2：老用户
     */
    private CheckUserTypeEnum checkUser(long userId) {
        /**
         * TODO 校验用户
         */
        return null;
    }


}
