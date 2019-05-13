package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.UserPreference;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPreferenceDaoTest {

    @Autowired
    private UserPreferenceDao preferenceDao;

    @Test
    public void insert() {
        UserPreference preference = new UserPreference(3, 1, 1);
        int result = preferenceDao.insert(preference);
        assertEquals(1, result);
    }

    @Test
    public void insertBatch(){
        List<UserPreference> preferenceList = Lists.newArrayList();
        preferenceList.add(new UserPreference(3, 1, 1));
        preferenceList.add(new UserPreference(3, 2, 1));
        int result = preferenceDao.insertBatch(preferenceList);
        Assert.assertEquals(2, result);
    }

    @Test
    public void listAll() {
        List<UserPreference> preferenceList = preferenceDao.listAll(0, 10);
        System.out.println(preferenceList);
    }

    @Test
    public void updateBatch() {
        List<UserPreference> preferenceList = Lists.newArrayList();
        preferenceList.add(new UserPreference(3, 1, 2));
        preferenceList.add(new UserPreference(3, 2, 2));
        int result = preferenceDao.updateBatch(preferenceList);
        Assert.assertEquals(2, result);
    }

}