package com.chenlinghong.graduation.api.vo;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.service.dto.RecommendQueueGoodsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 主页VO
 * @Author chenlinghong
 * @Date 2019/4/29 9:15
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePageVo implements Serializable {

    private static final long serialVersionUID = 47834696641007060L;

    /**
     * 所有目录数据,包括一级二级
     */
    private PageDto<GoodsCatalogOne> catalogPageDto;

    /**
     * 商品推荐队列DTO
     */
    private RecommendQueueGoodsDto recommendQueueGoodsDto;

    // /**
    //  * 基于用户推荐
    //  */
    // RecommendDto<RecommendGoodsDto> userBasedRecommend;
    //
    // /**
    //  * 基于物品推荐
    //  */
    // RecommendDto<RecommendGoodsDto> itemBasedRecommend;
    //
    // /**
    //  * SlopeOne推荐，基于评分推荐
    //  */
    // RecommendDto<RecommendGoodsDto> slopeOneRecommend;
    //
    // /**
    //  * 热门推荐
    //  */
    // PageDto<RecommendRankingGoods> popularRecommend;
    //
    // /**
    //  * 时令推荐
    //  */
    // RecommendDto<RecommendGoodsDto> seasonRecommend;
    //
    // /**
    //  * 基于用户标签的推荐
    //  */
    // RecommendDto<RecommendGoodsDto> userTagBasedRecommend;

}
