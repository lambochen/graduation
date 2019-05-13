package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.UserTag;
import lombok.NonNull;

import java.util.List;

/**
 * @Description 用户标签
 * @Author chenlinghong
 * @Date 2019/4/27 18:02
 * @Version V1.0
 */
public interface UserTagService extends IBaseService<UserTag> {

    /**
     * 批量新增
     *
     * @param userTagList
     * @return
     */
    int insert(@NonNull List<UserTag> userTagList);

    /**
     * 根据用户获取所有
     *
     * @param userId
     * @return
     */
    PageDto<UserTag> listByUser(long userId);
}
