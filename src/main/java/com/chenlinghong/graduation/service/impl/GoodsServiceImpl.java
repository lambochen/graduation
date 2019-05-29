package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.repository.dao.GoodsDao;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品基本信息
 * @Author chenlinghong
 * @Date 2019/4/14 15:36
 * @Version V1.0
 */
@Slf4j
@Service(value = "goodsService")
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

    @Override
    public PageDto<Goods> listByCatalogOne(int catalogOneId, long pageNo, long pageSize) {
        List<Goods> goodsList = goodsDao.listByCatalogOne(catalogOneId, (pageNo - 1) * pageSize, pageSize);
        long total = goodsDao.countByCatalogOne(catalogOneId);
        return new PageDto<>(goodsList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<Goods> listByCatalogTwo(int catalogTwoId, long pageNo, long pageSize) {
        List<Goods> goodsList = goodsDao.listByCatalogTwo(catalogTwoId, (pageNo - 1) * pageSize, pageSize);
        long total = goodsDao.countByCatalogTwo(catalogTwoId);
        return new PageDto<>(goodsList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<Goods> searchByName(String name, long pageNo, long pageSize) {
        List<Goods> goodsList = goodsDao.listByName(name, (pageNo - 1) * pageSize, pageSize);
        long count = goodsDao.countByName(name);
        return new PageDto<>(goodsList, pageNo, pageSize, count);
    }

    @Override
    public boolean isNotGoods(long goodsId) {
        return !isGoods(goodsId);
    }

    @Override
    public boolean isGoods(long goodsId) {
        int goodsCount = goodsDao.countByGoodsId(goodsId);
        if (goodsCount == NumericConstant.ONE) {
            return true;
        }
        return false;
    }

    @Override
    public List<Goods> listByIdList(List<Long> goodsIdList) {
        return goodsDao.listByIdList(goodsIdList);
    }

    @Override
    public PageDto<Goods> listByCatalogTwoList(List<GoodsCatalogTwo> data) {
        return listByCatalogTwoList(data, NumericConstant.ONE, NumericConstant.TEN);
    }

    @Override
    public PageDto<Goods> listByCatalogTwoList(List<GoodsCatalogTwo> data, long pageNo, long pageSize) {
        List<Goods> goodsList = goodsDao.listByCatalogTwoList(data, (pageNo - 1) * pageSize, pageSize);
        return new PageDto<>(goodsList);
    }
}
