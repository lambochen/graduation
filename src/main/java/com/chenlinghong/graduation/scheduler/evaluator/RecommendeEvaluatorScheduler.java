package com.chenlinghong.graduation.scheduler.evaluator;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/5/30 4:19
 * @Version V1.0
 */
public interface RecommendeEvaluatorScheduler {

    /**
     * 计算评分，通过推荐器类型
     */
    double evaluateScore(RecommendTypeEnum typeEnum) throws TasteException;

    /**
     * 计算查全率、查准率
     *
     * @param typeEnum
     * @return
     * @throws TasteException
     */
    IRStatistics evaluateIRStatistics(RecommendTypeEnum typeEnum) throws TasteException;

    /**
     * 评估
     *
     * @param typeEnum
     * @throws TasteException
     */
    void evaluate(RecommendTypeEnum typeEnum) throws TasteException;



}
