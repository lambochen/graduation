package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.scheduler.evaluator.RecommendeEvaluatorScheduler;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendeEvaluatorServiceImplTest {

    @Autowired
    private RecommendeEvaluatorScheduler recommendeEvaluatorScheduler;

    @Test
    public void evaluateScoreByRecommender() throws TasteException {
        double score = recommendeEvaluatorScheduler.evaluateScore(RecommendTypeEnum.ITEM_BASED_RECOMMEND);
        System.out.println(score);
    }

    @Test
    public void evaluateIRStatistics() throws TasteException {
        IRStatistics irStatistics = recommendeEvaluatorScheduler.evaluateIRStatistics(RecommendTypeEnum.ITEM_BASED_RECOMMEND);
        System.out.println(irStatistics);
    }
}