package com.chenlinghong.graduation.microscope.sniffer.ranking;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;

/**
 * @Description 商品 推荐排名嗅探器
 * @Author chenlinghong
 * @Date 2019/5/3 11:16
 * @Version V1.0
 */
public interface RecommendRankingGoodsSniffer extends RecommendRankingSniffer {

    /**
     * 加入排名
     *
     * @param goodsId
     * @return
     */
    Boolean pushRanking(long goodsId);



}
