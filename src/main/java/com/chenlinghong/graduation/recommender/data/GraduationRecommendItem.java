package com.chenlinghong.graduation.recommender.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 推荐项
 * @Author chenlinghong
 * @Date 2019/4/28 9:44
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraduationRecommendItem implements Serializable {

    private static final long serialVersionUID = 4974953057786777499L;

    /**
     * 用户Id
     */
    private long userId;

    /**
     * 物品ID
     */
    private long itemId;

    /**
     * 偏好
     */
    private float preference;
}
