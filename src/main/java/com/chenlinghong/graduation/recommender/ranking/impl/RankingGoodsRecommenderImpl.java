package com.chenlinghong.graduation.recommender.ranking.impl;

import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.ranking.RankingGoodsRecommender;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 热门推荐实现类
 * @Author chenlinghong
 * @Date 2019/5/3 16:31
 * @Version V1.0
 */
@Slf4j
@Service
public class RankingGoodsRecommenderImpl implements RankingGoodsRecommender {

    @Autowired
    private MyRedisUtil redisUtil;

    // @Autowired
    // private RecommendRankingGoodsService rankingGoodsService;

    @Override
    public Long getByGoods(long goodsId) {
        return redisUtil.rankToRankingGoods(goodsId);
    }

    @Override
    public List<RecommendRankingGoods> topN(int n) {
        return range(NumericConstant.ONE, n);
    }

    @Override
    public List<RecommendRankingGoods> range(int pageNo, int pageSize) {
        if (pageNo <= 0 || pageSize < 0) {
            log.error("RecommendRankingGoodsSniffer#range: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return Lists.newArrayList();
        }
        return redisUtil.rangeWithScoresToRankingGoods((pageNo - 1) * pageSize, pageSize);
    }

    // /**
    //  * 填充Goods数据
    //  *
    //  * @param rankingGoodsList
    //  * @return
    //  */
    // private PageDto<RecommendRankingGoods> fillGoods(List<RecommendRankingGoods> rankingGoodsList,
    //                                                  int pageNo, int pageSize) {
    //     List<RecommendRankingGoods> dbData = rankingGoodsService.listByGoodsList(rankingGoodsList);
    //     int total = rankingGoodsService.count();
    //     return new PageDto<>(dbData, pageNo, pageSize, total);
    // }
}
