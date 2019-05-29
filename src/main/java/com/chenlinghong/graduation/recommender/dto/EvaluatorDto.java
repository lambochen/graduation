package com.chenlinghong.graduation.recommender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 评估结果
 * @Author chenlinghong
 * @Date 2019/5/30 0:53
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatorDto implements Serializable {

    private static final long serialVersionUID = 5513280053439582357L;

    /**
     * 查准率
     */
    private double precision;

    /**
     * 召回率
     */
    private double recall;
}
