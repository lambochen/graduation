package com.chenlinghong.graduation.service.dto;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 商品推荐队列Dto
 * @Author chenlinghong
 * @Date 2019/5/3 19:33
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendQueueGoodsDto implements Serializable {

    private static final long serialVersionUID = -5705154927445198987L;

    /**
     * 基于用户推荐
     */
    private PageDto<RecommendQueueGoods> userBasedRecommend;

    /**
     * 基于物品推荐
     */
    private PageDto<RecommendQueueGoods> itemBasedRecommend;

    /**
     * SlopeOne推荐，基于评分推荐
     */
    private PageDto<RecommendQueueGoods> slopeOneRecommend;

    /**
     * 热门推荐
     */
    private PageDto<RecommendRankingGoods> popularRecommend;

    /**
     * 时令推荐
     */
    private PageDto<RecommendQueueGoods> seasonRecommend;

    /**
     * 基于用户标签的推荐
     */
    private PageDto<RecommendQueueGoods> userTagBasedRecommend;
    
    
}
