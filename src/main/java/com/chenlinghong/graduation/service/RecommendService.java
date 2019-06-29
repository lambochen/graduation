package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 推荐Service
 * @Author chenlinghong
 * @Date 2019/5/4 11:12
 * @Version V1.0
 */
public interface RecommendService {

    /**
     * 热门推荐
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommendRankingGoods> popularRecommend(long pageNo, long pageSize);

    /**
     * 时令推荐
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommendQueueGoods> seasonRecommend(long pageNo, long pageSize);

    /**
     * 根据推荐类型获取,从推荐队列中获取
     *
     * @param typeEnum
     * @return
     */
    PageDto<RecommendQueueGoods> recommendByType(RecommendTypeEnum typeEnum, long userId) throws TasteException;

}
