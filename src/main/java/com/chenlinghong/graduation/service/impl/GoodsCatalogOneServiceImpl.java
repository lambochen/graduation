package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.repository.dao.GoodsCatalogOneDao;
import com.chenlinghong.graduation.repository.dao.GoodsCatalogTwoDao;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
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

    @Autowired
    private GoodsCatalogTwoDao goodsCatalogTwo;

    @Override
    public List<GoodsCatalogOne> listAllGoodsCatalogOne() {
        List<GoodsCatalogOne> goodsCatalogOnes = goodsCatalogOne.listAllGoodsCatalogOne();
        for(GoodsCatalogOne goodsCatalogOne:goodsCatalogOnes){
            List<GoodsCatalogTwo> goodsCatalogTwos = goodsCatalogTwo.listByGoodsCatalogOneId(goodsCatalogOne.getId());
            goodsCatalogOne.setCatalogTwoList(goodsCatalogTwos);
        }
        return goodsCatalogOnes;
    }
}
