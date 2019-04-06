package com.chenlinghong.graduation.util;

import com.chenlinghong.graduation.api.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRedisUtilTest {

    @Autowired
    private MyRedisUtil redisUtil;

    @Test
    public void put() {
    }

    @Test
    public void putSmsCode() {
    }

    @Test
    public void getUserVo() {
        UserVo userVo = redisUtil.getUserVo("13008142306");
    }

    @Test
    public void getUser() {
    }

    @Test
    public void getUserByTelephone() {
    }

    @Test
    public void getSmsCode() {
    }
}