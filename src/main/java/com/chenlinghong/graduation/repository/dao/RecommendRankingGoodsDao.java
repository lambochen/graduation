package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;

/**
 * @Description 商品推荐排行榜
 * @Author chenlinghong
 * @Date 2019/5/2 21:07
 * @Version V1.0
 */
public interface RecommendRankingGoodsDao extends IBaseDao<RecommendRankingGoods> {
    /**
     * 根据商品获取记录数
     *
     * @param goodsId
     * @return
     */
    int countByGoods(Long goodsId);
}
