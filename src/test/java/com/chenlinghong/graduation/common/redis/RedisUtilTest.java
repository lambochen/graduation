package com.chenlinghong.graduation.common.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        String key = "set-test";
        redisUtil.set(key, "12345");
        String setTest = redisUtil.get(key);
        System.out.println(setTest);
    }
}