package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserBehavior;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description 用户行为
 * @Author chenlinghong
 * @Date 2019/4/24 9:14
 * @Version V1.0
 */
public interface UserBehaviorDao extends IBaseDao<UserBehavior> {

    /**
     * 写入数据
     *
     * @param goodsId
     * @param userId
     * @param behavior
     * @param frequency
     * @return
     */
    int batchInsert(@Param("goodsId") long goodsId, @Param("userId") long userId,
                    @Param("behavior") int behavior, @Param("frequency") List<Integer> frequency);

    /**
     * 批量写入数据
     *
     * @param behaviorList
     * @return
     */
    int batchInsertByUserBehavior(@Param("behaviorList") List<UserBehavior> behaviorList);

    /**
     * 通过用户、商品、开始时间获取
     *
     * @param userId
     * @param goodsId
     * @param startTime
     * @return
     */
    List<UserBehavior> listByUserAndGoodsAndStartTime(@Param("userId") long userId, @Param("goodsId") long goodsId,
                                                      @Param("startTime") Date startTime);

    /**
     * 通过用户、开始时间获取用户所有行为记录
     *
     * @param userId
     * @param startTime
     * @return
     */
    List<UserBehavior> listByUserAndStartTime(@Param("userId") long userId, @Param("startTime") Date startTime);

    /**
     * 根据用户获取
     *
     * @param userId
     * @param offset
     * @param rows
     * @return
     */
    List<UserBehavior> listByUser(@Param("userId") long userId, @Param("offset") long offset, @Param("rows") long rows);

    /**
     * 根据用户获取记录数
     *
     * @param userId
     * @return
     */
    long countByUser(@Param("userId") long userId);


}
