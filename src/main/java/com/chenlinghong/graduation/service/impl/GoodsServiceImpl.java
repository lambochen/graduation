package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.repository.dao.GoodsDao;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Goods> listByGoodsCatalogOneId(Long goodsCatalogOneId) {
        if (goodsCatalogOneId != null) {
            return goods.listByGoodsCatalogOneId(goodsCatalogOneId);
        }
        return null;
    }

    @Override
    public List<Goods> listByGoodsCatalogTwoId(Long goodsCatalogTwoId) {
        if (goodsCatalogTwoId != null) {
            return goods.listByGoodsCatalogTwoId(goodsCatalogTwoId);
        }
        return null;
    }
}
