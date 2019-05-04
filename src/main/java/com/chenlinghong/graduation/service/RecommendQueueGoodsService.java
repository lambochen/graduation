package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import com.chenlinghong.graduation.service.dto.RecommendQueueGoodsDto;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

/**
 * @Description 商品推荐队列
 * @Author chenlinghong
 * @Date 2019/5/3 11:01
 * @Version V1.0
 */
public interface RecommendQueueGoodsService extends IBaseService<RecommendQueueGoods> {

    /**
     * 批量写入
     *
     * @param recommendQueueGoodsList 推荐队列商品列表
     * @return
     */
    int insert(List<RecommendQueueGoods> recommendQueueGoodsList);

    /**
     * 根据用户和推荐类型获取
     *
     * @param userId
     * @param typeEnum
     * @return
     */
    PageDto<RecommendQueueGoods> listByUserAndType(long userId, RecommendTypeEnum typeEnum, int pageNo, int pageSize) throws TasteException;

    /**
     * 根据用户和推荐类型获取，获取默认条数
     *
     * @param userId
     * @param typeEnum
     * @return
     */
    PageDto<RecommendQueueGoods> listByUserAndType(long userId, RecommendTypeEnum typeEnum) throws TasteException;


    /**
     * 根据用户获取首页推荐结果
     *
     * @param userId
     * @return
     */
    RecommendQueueGoodsDto listByUser(long userId) throws TasteException;

    /**
     * 标记为已读
     *
     * @param userId
     * @param userBasedRecommend
     * @return
     */
    int markRead(long userId, RecommendTypeEnum userBasedRecommend);

    /**
     * 转换数据
     * @param recommendDto
     * @return
     */
    PageDto<RecommendQueueGoods> converter(RecommendDto<RecommendGoodsDto> recommendDto);
}
