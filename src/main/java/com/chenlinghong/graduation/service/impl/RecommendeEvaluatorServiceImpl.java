package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.dao.RecommenderEvalutorDao;
import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import com.chenlinghong.graduation.scheduler.evaluator.RecommendeEvaluatorScheduler;
import com.chenlinghong.graduation.service.RecommendeEvaluatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐评估器Service
 * @Author chenlinghong
 * @Date 2019/5/30 1:30
 * @Version V1.0
 */
@Service
@Slf4j
public class RecommendeEvaluatorServiceImpl implements RecommendeEvaluatorService {

    @Autowired
    private RecommenderEvalutorDao recommenderEvalutorDao;

    @Autowired
    private RecommendeEvaluatorScheduler recommendeEvaluatorScheduler;

    @Override
    public PageDto<RecommenderEvalutor> listAll(long pageNo, long pageSize) throws TasteException {
        List<RecommenderEvalutor> evaluatorList = recommenderEvalutorDao.listAll((pageNo - 1) * pageSize, pageSize);
        long totalCount = recommenderEvalutorDao.count();
        /**
         * 更新数据
         */
        recommendeEvaluatorScheduler.evaluate(RecommendTypeEnum.USER_BASED_RECOMMEND);
        recommendeEvaluatorScheduler.evaluate(RecommendTypeEnum.ITEM_BASED_RECOMMEND);
        recommendeEvaluatorScheduler.evaluate(RecommendTypeEnum.SLOPE_ONE_RECOMMEND);
        return new PageDto<>(evaluatorList, pageNo, pageSize, totalCount);
    }

    @Override
    public PageDto<RecommenderEvalutor> listByType(RecommendTypeEnum typeEnum, long pageNo, long pageSize) throws TasteException {
        List<RecommenderEvalutor> evaluatorList = recommenderEvalutorDao.listByType(typeEnum.getCode(), (pageNo - 1) * pageSize, pageSize);
        long totalCount = recommenderEvalutorDao.countByType(typeEnum.getCode());
        /**
         * 更新数据
         */
        recommendeEvaluatorScheduler.evaluate(typeEnum);
        return new PageDto<>(evaluatorList, pageNo, pageSize, totalCount);
    }
}
