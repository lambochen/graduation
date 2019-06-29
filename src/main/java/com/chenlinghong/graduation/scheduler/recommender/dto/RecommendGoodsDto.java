package com.chenlinghong.graduation.scheduler.recommender.dto;

import com.chenlinghong.graduation.repository.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 推荐商品DTO
 * @Author chenlinghong
 * @Date 2019/5/1 9:08
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendGoodsDto implements Serializable {

    private static final long serialVersionUID = -1594566968277730709L;

    /**
     * 推荐商品信息
     */
    private Goods goods;

    /**
     * 评分
     */
    private Float score;
}
