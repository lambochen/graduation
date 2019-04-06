package com.chenlinghong.graduation.common.redis;

import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.util.RedisKeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisKeyUtilTest {

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Test
    public void generateKey() {
        User user = new User();
        user.setId(1000L);

        String redisKey = redisKeyUtil.generateKey(user);
        System.out.println(redisKey);
    }
}