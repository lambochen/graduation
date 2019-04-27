package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用户标签持久化
 * @Author chenlinghong
 * @Date 2019/4/23 22:35
 * @Version V1.0
 */
public interface UserTagDao extends IBaseDao<UserTag> {

    /**
     * 批量新增
     *
     * @param userTagList
     * @return
     */
    int insertBatch(@Param("userTagList") List<UserTag> userTagList);

    /**
     * 根据用户获取
     *
     * @param userId
     * @return
     */
    List<UserTag> listByUser(long userId);
}
