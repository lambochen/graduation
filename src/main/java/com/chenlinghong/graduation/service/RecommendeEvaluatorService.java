package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 推荐评估器
 * @Author chenlinghong
 * @Date 2019/5/30 1:21
 * @Version V1.0
 */
public interface RecommendeEvaluatorService {

    /**
     * 分页获取
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageDto<RecommenderEvalutor> listAll(long pageNo, long pageSize) throws TasteException;

    /**
     * 根据类型获取
     *
     * @param pageNo
     * @param pageSize
     * @param typeEnum
     * @return
     */
    PageDto<RecommenderEvalutor> listByType(RecommendTypeEnum typeEnum, long pageNo, long pageSize) throws TasteException;
}
