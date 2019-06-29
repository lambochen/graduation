package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.GoodsComment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCommentDaoTest {

    @Autowired
    private GoodsCommentDao goodsCommentDao;

    @Test
    public void insert() {
        GoodsComment goodsComment = new GoodsComment(1, 2, "test", 1);
        int result = goodsCommentDao.insert(goodsComment);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listByGoods(){
        List<GoodsComment> commentList = goodsCommentDao.listByGoods(1,0,10);
        System.out.println(commentList);
    }

}