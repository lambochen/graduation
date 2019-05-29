package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommenderEvalutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommenderEvalutorDaoTest {

    @Autowired
    private RecommenderEvalutorDao evalutorDao;

    @Test
    public void insert() {
        RecommenderEvalutor evalutor = new RecommenderEvalutor();
        evalutor.setScore(0.8);
        evalutor.setType(RecommendTypeEnum.USER_BASED_RECOMMEND.getCode());
        int result = evalutorDao.insert(evalutor);
        assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<RecommenderEvalutor> evalutorList = evalutorDao.listAll(0, 10);
        System.out.println(evalutorList);
    }

}