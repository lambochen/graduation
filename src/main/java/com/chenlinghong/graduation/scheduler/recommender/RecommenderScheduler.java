package com.chenlinghong.graduation.scheduler.recommender;

import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.scheduler.Scheduler;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendDto;
import com.chenlinghong.graduation.scheduler.recommender.dto.RecommendGoodsDto;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

/**
 * @Description 推荐算法执行器
 * @Author chenlinghong
 * @Date 2019/5/3 20:22
 * @Version V1.0
 */
public interface RecommenderScheduler extends Scheduler {

    /**
     * 推荐固定条数
     *
     * @param userId 用户ID
     * @return
     */
    RecommendDto recommend(final long userId) throws TasteException;

    /**
     * 推荐
     *
     * @param userId       用户ID
     * @param recommendNum 推荐条数
     * @return
     */
    RecommendDto recommend(final long userId, final int recommendNum) throws TasteException;

    /**
     * 转换数据
     *
     * @param recommendDto
     * @return
     */
    List<RecommendQueueGoods> converter(RecommendDto<RecommendGoodsDto> recommendDto);

    /**
     * 刷新推荐队列
     *
     * @param userId 用户ID
     * @return
     */
    Long refreshRecommendQueue(long userId) throws TasteException;

}
