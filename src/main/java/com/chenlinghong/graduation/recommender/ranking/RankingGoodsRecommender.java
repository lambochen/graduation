package com.chenlinghong.graduation.recommender.ranking;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.recommender.Recommender;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;

/**
 * @Description 热门推荐
 * @Author chenlinghong
 * @Date 2019/5/3 16:31
 * @Version V1.0
 */
public interface RankingGoodsRecommender extends Recommender {

    /**
     * 通过商品ID获取
     *
     * @param goodsId
     * @return
     */
    Long getByGoods(long goodsId);

    /**
     * top n
     *
     * @param n
     * @return
     */
    PageDto<RecommendRankingGoods> topN(int n);

    /**
     * range
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommendRankingGoods> range(int pageNo, int pageSize);

}
