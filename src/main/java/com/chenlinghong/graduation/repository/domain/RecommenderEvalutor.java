package com.chenlinghong.graduation.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 推荐器评估
 * @Author chenlinghong
 * @Date 2019/5/30 2:45
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommenderEvalutor extends BaseDomain {

    private static final long serialVersionUID = 814619240226812791L;

    /**
     * 评分
     */
    private Double score;

    /**
     * 查准率
     */
    private Double precision;

    /**
     * 召回率、查全率
     */
    private Double recall;

    /**
     * 推荐类型
     */
    private Integer type;
}
