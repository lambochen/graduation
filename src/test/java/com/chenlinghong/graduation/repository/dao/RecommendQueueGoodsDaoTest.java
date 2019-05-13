package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendQueueGoodsDaoTest {

    @Autowired
    private RecommendQueueGoodsDao recommendQueueGoodsDao;

    @Test
    public void insert() {
        RecommendQueueGoods recommendQueueGoods = new RecommendQueueGoods();
        recommendQueueGoods.setGoodsId(1L);
        recommendQueueGoods.setUserId(3L);
        recommendQueueGoods.setRecommendType(RecommendTypeEnum.USER_BASED_RECOMMEND.getCode());
        assertEquals(1, recommendQueueGoodsDao.insert(recommendQueueGoods));
    }

    @Test
    public void listAll() {
        List<RecommendQueueGoods> recommendQueueGoodsList = recommendQueueGoodsDao.listAll(0, 10);
        System.out.println(recommendQueueGoodsList);
    }

}