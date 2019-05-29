package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;

import java.util.List;

/**
 * @Description 推荐排行榜
 * @Author chenlinghong
 * @Date 2019/5/3 10:37
 * @Version V1.0
 */
public interface RecommendRankingGoodsService extends IBaseService<RecommendRankingGoods> {

    /**
     * 增加rank, 增加默认值
     *
     * @param goodsId
     * @return
     */
    int incrementRank(long goodsId);

    /**
     * 增加rank
     *
     * @param goodsId
     * @param rank
     * @return
     */
    int incrementRank(long goodsId, int rank);

    /**
     * 获取商品信息
     *
     * @param rankingGoodsList
     * @return
     */
    List<RecommendRankingGoods> listByGoodsList(List<RecommendRankingGoods> rankingGoodsList);

    /**
     * 获取总记录数
     *
     * @return
     */
    long count();
}
