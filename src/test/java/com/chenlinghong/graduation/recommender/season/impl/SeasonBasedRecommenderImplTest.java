package com.chenlinghong.graduation.recommender.season.impl;

import com.chenlinghong.graduation.recommender.season.SeasonBasedRecommender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeasonBasedRecommenderImplTest {

    @Autowired
    private SeasonBasedRecommender recommender;

    @Test
    public void recommend() {
        System.out.println(recommender.recommend());
    }
}