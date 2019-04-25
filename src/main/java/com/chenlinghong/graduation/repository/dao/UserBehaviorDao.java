package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserBehavior;
import org.apache.ibatis.annotations.Param;

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
}
