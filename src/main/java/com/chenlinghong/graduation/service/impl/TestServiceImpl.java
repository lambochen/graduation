package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.TestDao;
import com.chenlinghong.graduation.repository.domain.TestBean;
import com.chenlinghong.graduation.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 测试类
 * @Author chenlinghong
 * @Date 2019/3/13 20:55
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public PageDto<TestBean> listAll(long pageNo, long pageSize) {
        long offset = (pageNo - 1) * pageSize;
        long rows = pageSize;
        List<TestBean> beanList = testDao.listAll((int) offset, (int) rows);
        int totalCount = testDao.count();
        return new PageDto<>(beanList, pageNo, pageSize, totalCount);
    }
}
