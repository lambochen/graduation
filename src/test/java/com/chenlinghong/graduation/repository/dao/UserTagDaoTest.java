package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTagDaoTest {

    @Autowired
    private UserTagDao tagDao;

    @Test
    public void insert() {
        UserTag tag = new UserTag(3L, 1L, 1L);
        int result = tagDao.insert(tag);
        assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<UserTag> tagList = tagDao.listAll(0, 10);
        System.out.println(tagList);
    }

}