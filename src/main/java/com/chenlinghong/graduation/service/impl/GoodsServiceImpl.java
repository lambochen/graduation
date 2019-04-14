package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.GoodsDao;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 商品基本信息
 * @Author chenlinghong
 * @Date 2019/4/14 15:36
 * @Version V1.0
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsCatalogService catalogService;

    @Override
    public int insert(Goods goods) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public Goods getById(long id) {
        return goodsDao.getById(id);
    }

    @Override
    public PageDto<Goods> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(Goods goods) {
        return 0;
    }
}
