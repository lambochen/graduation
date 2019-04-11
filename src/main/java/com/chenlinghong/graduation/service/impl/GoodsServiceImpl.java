package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.GoodsDao;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:DxlinY
 * @Description：
 * @Date: 2019/4/9
 * @Time: 17:33
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goods;

    @Override
    public PageDto<Goods> listByGoodsCatalogOneId(long goodsCatalogOneId) {
        return new PageDto<>(goods.listByGoodsCatalogOneId(goodsCatalogOneId));
    }

    @Override
    public PageDto listByGoodsCatalogTwoId(long goodsCatalogTwoId) {
        return new PageDto<>(goods.listByGoodsCatalogTwoId(goodsCatalogTwoId));
    }

    @Override
    public Goods getGoodsById(long id) {
        return goods.getGoodsById(id);
    }
}
