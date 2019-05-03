package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据商品获取
     *
     * @param goodsId
     * @return
     */
    RecommendRankingGoods getByGoods(Long goodsId);

    /**
     * 获取商品信息
     *
     * @param rankingGoodsList
     * @return
     */
    List<RecommendRankingGoods> listByGoodsList(@Param("rankingGoodsList") List<RecommendRankingGoods> rankingGoodsList);
}
