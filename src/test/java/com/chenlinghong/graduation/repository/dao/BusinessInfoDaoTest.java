package com.chenlinghong.graduation.repository.dao;


import com.chenlinghong.graduation.repository.domain.BusinessInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessInfoDaoTest {

    @Autowired
    private BusinessInfoDao businessInfoDao;

    @Test
    public void insert() {
        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setName("test");
        businessInfo.setAvatarUrl("test");
        businessInfo.setUserId(3L);
        businessInfo.setTelephone("13008142307");
        int result = businessInfoDao.insert(businessInfo);
        Assert.assertEquals(1, result);
    }

    @Test
    public void listAll() {
        List<BusinessInfo> businessInfoList = businessInfoDao.listAll(0, 10);
        System.out.println(businessInfoList);
    }

}