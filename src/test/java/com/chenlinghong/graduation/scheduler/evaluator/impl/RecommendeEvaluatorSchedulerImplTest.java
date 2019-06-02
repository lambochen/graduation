package com.chenlinghong.graduation.scheduler.evaluator.impl;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.scheduler.evaluator.RecommendeEvaluatorScheduler;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendeEvaluatorSchedulerImplTest {

    @Autowired
    private RecommendeEvaluatorScheduler recommendeEvaluatorScheduler;

    @Test
    public void evaluateScore() throws TasteException {
        double score = recommendeEvaluatorScheduler.evaluateScore(RecommendTypeEnum.USER_BASED_RECOMMEND);
        System.out.println(score);

    }

    @Test
    public void evaluateIRStatistics() {
    }

    @Test
    public void evaluate() {
    }
}