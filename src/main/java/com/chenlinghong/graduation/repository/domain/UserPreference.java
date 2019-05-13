package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户偏好
 * @Author chenlinghong
 * @Date 2019/4/24 9:09
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPreference extends BaseDomain {

    private static final long serialVersionUID = 181230263854707171L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 物品ID
     */
    private Long goodsId;

    /**
     * 偏好，数值越大，越偏爱
     */
    private Integer preference;

    public UserPreference(long userId, long goodsId, int preference) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.preference = preference;
    }
}
