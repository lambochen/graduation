package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 商品推荐队列
 * @Author chenlinghong
 * @Date 2019/5/2 21:43
 * @Version V1.0
 */
public interface RecommendQueueGoodsDao extends IBaseDao<RecommendQueueGoods> {

    /**
     * 批量写入
     *
     * @param recommendQueueGoodsList
     * @return
     */
    int insertBatch(@Param("recommendQueueGoodsList") List<RecommendQueueGoods> recommendQueueGoodsList);

    /**
     * 根据用户和推荐类型获取
     *
     * @param userId        用户ID
     * @param recommendType 推荐类型
     * @param offset
     * @param rows
     * @return
     */
    List<RecommendQueueGoods> listByUserAndType(@Param("userId") long userId,
                                                @Param("recommendType") int recommendType,
                                                @Param("offset") int offset,
                                                @Param("rows") int rows);

    /**
     * 根据用户和推荐类型获取记录数
     *
     * @param userId
     * @param recommendType
     * @return
     */
    long countByUserAndType(@Param("userId") long userId, @Param("recommendType") int recommendType);

    /**
     * 标记为已读
     *
     * @param userId
     * @param recommendType
     * @return
     */
    int markRead(@Param("userId") long userId, @Param("recommendType") int recommendType);
}
