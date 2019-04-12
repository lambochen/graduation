package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.GoodsCatalogOneDao;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.service.GoodsCatalogOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：
 * @Date: 2019/4/9
 * @Time: 21:50
 */

@Service
public class GoodsCatalogOneServiceImpl implements GoodsCatalogOneService {

    @Autowired
    private GoodsCatalogOneDao goodsCatalogOne;

    @Override
    public PageDto<GoodsCatalogOne> listAllGoodsCatalogOne() {
        int count = goodsCatalogOne.count();
        List<GoodsCatalogOne> goodsCatalogOnes = goodsCatalogOne.listAll(0, count);
        return new PageDto<>(goodsCatalogOnes);
    }
}
