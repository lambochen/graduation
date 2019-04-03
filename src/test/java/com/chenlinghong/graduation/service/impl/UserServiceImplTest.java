package com.chenlinghong.graduation.service.impl;


import com.chenlinghong.graduation.api.vo.UserVo;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setPassword("123456");
        user.setTelephone("13008142306");
        user.setType(3);
        user.setBirthday(new Date());
        user.setAvatarUrl("test");
        user.setCity("Chengdu");
        user.setCountry("China");
        user.setProvince("Sichuan");
        user.setRealName("chenlinghong");
        user.setNickName("chenlinghong");
        user.setLatitude("30.00");
        user.setLongitude("45.00");
        user.setPosition("xhu university");
        user.setDescription("test");
        user.setGender(1);
        int result = userService.insert(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void loginByPwd() {
        String telephone = "13008142306";
        String password = "123456";
        UserVo userVo = userService.loginByPwd(telephone, password);
        System.out.println(userVo);
    }


}