package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.GoodsCatalogOneDao;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品目录
 * @Author chenlinghong
 * @Date 2019/4/14 14:29
 * @Version V1.0
 */
@Slf4j
@Service
public class GoodsCatalogServiceImpl implements GoodsCatalogService {

    @Autowired
    private GoodsCatalogOneDao catalogOneDao;

    @Override
    public PageDto<GoodsCatalogOne> listAll(int pageNo, int pageSize) {
        List<GoodsCatalogOne> catalogOneList = catalogOneDao.listAll((pageNo - 1) * pageSize, pageSize);
        int total = catalogOneDao.count();
        return new PageDto<>(catalogOneList, pageNo, pageSize, total);
    }
}
