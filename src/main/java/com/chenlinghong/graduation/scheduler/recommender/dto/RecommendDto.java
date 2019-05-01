package com.chenlinghong.graduation.scheduler.recommender.dto;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 推荐数据DTO
 * @Author chenlinghong
 * @Date 2019/4/30 22:27
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDto<T> implements Serializable {

    private static final long serialVersionUID = 1097035396859030390L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户基本信息
     */
    private User user;

    /**
     * 推荐数据
     */
    private PageDto<T> data;
}
