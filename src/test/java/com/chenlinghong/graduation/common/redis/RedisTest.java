package com.chenlinghong.graduation.common.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RedisTest
 * @Description Redis测试
 * @Author chenlinghong
 * @Date 2019/4/4 16:11
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void connection() {
        stringRedisTemplate.opsForValue().set("test", "1000");
        String test = stringRedisTemplate.opsForValue().get("test");
        Assert.assertEquals(1000, Integer.parseInt(test));
    }




}
