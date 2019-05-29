package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;

/**
 * @Description 推荐评估器
 * @Author chenlinghong
 * @Date 2019/5/30 1:21
 * @Version V1.0
 */
public interface RecommendeEvaluatorService {

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
     * 分页获取
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommenderEvalutor> listAll(long pageNo, long pageSize);

    /**
     * 根据类型获取
     *
     * @param pageNo
     * @param pageSize
     * @param typeEnum
     * @return
     */
    PageDto<RecommenderEvalutor> listByType(RecommendTypeEnum typeEnum,long pageNo, long pageSize);
}
