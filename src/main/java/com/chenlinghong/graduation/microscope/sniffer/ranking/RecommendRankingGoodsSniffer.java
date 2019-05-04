package com.chenlinghong.graduation.microscope.sniffer.ranking;

/**
 * @Description 商品 推荐排名嗅探器
 * @Author chenlinghong
 * @Date 2019/5/3 11:16
 * @Version V1.0
 */
public interface RecommendRankingGoodsSniffer extends RecommendRankingSniffer {

    /**
     * TODO 加入排名,需要埋点
     *
     * @param goodsId
     * @return
     */
    Boolean pushRanking(long goodsId);



}
