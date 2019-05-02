package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendRankingGoodsDaoTest {

    @Autowired
    private RecommendRankingGoodsDao recommendRankingGoodsDao;

    @Test
    public void insert() {
        RecommendRankingGoods rankingGoods = new RecommendRankingGoods();
        rankingGoods.setGoodsId(2L);
        rankingGoods.setRanking(1);
        int result = recommendRankingGoodsDao.insert(rankingGoods);
        assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<RecommendRankingGoods> rankingGoodsList = recommendRankingGoodsDao.listAll(0, 10);
        System.out.println(rankingGoodsList);
    }

}