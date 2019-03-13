package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.TestBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDaoTest {

    @Autowired
    private TestDao testDao;

    @Test
    public void insert() {

        TestBean testBean = new TestBean();
        testBean.setName("test");
        int result = testDao.insert(testBean);
        Assert.assertEquals(1, result);

    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void listAll() {
        List<TestBean> beanList = testDao.listAll(0,10);
        System.out.println(beanList);
    }

    @Test
    public void count() {
    }

    @Test
    public void update() {
    }
}