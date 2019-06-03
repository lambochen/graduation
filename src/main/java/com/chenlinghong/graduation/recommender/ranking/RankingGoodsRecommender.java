package com.chenlinghong.graduation.recommender.ranking;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.recommender.Recommender;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.RecommendRankingGoodsService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 热门推荐
 * @Author chenlinghong
 * @Date 2019/5/3 16:31
 * @Version V1.0
 */
@Slf4j
@Service
public class RankingGoodsRecommender implements Recommender {

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private RecommendRankingGoodsService rankingGoodsService;

    /**
     * 通过商品ID获取
     *
     * @param goodsId
     * @return
     */
    public Long getByGoods(long goodsId) {
        return redisUtil.rankToRankingGoods(goodsId);
    }

    /**
     * top n
     *
     * @param n
     * @return
     */
    public PageDto<RecommendRankingGoods> topN(int n) {
        return range(NumericConstant.ONE, n);
    }

    /**
     * range
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageDto<RecommendRankingGoods> range(long pageNo, long pageSize) {
        if (pageNo <= 0 || pageSize <= 0) {
            log.error("RecommendRankingGoodsSniffer#range: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return new PageDto<>();
        }
        List<RecommendRankingGoods> rankingGoodsList =
                redisUtil.rangeWithScoresToRankingGoods((pageNo - 1) * pageSize, pageSize - 1);
        return fillGoods(rankingGoodsList, pageNo, pageSize);
    }

    /**
     * 填充Goods数据
     *
     * @param rankingGoodsList
     * @return
     */
    private PageDto<RecommendRankingGoods> fillGoods(List<RecommendRankingGoods> rankingGoodsList,
                                                     long pageNo, long pageSize) {
        List<RecommendRankingGoods> dbData = rankingGoodsService.listByGoodsList(rankingGoodsList);
        long total = rankingGoodsService.count();
        return new PageDto<>(dbData, pageNo, pageSize, total);
    }

}
