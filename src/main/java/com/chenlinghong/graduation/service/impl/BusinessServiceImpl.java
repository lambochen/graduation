package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.BusinessInfoDao;
import com.chenlinghong.graduation.repository.domain.BusinessInfo;
import com.chenlinghong.graduation.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 商户信息
 * @Author chenlinghong
 * @Date 2019/4/15 1:18
 * @Version V1.0
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessInfoDao businessInfoDao;

    @Override
    public int insert(BusinessInfo businessInfo) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public BusinessInfo getById(long id) {
        return businessInfoDao.getById(id);
    }

    @Override
    public PageDto<BusinessInfo> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(BusinessInfo businessInfo) {
        return 0;
    }
}
