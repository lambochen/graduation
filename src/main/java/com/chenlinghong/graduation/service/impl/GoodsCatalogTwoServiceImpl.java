package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.repository.dao.GoodsCatalogTwoDao;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.service.GoodsCatalogTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：
 * @Date: 2019/4/9
 * @Time: 15:27
 */
@Service
public class GoodsCatalogTwoServiceImpl implements GoodsCatalogTwoService {

    @Autowired
    private GoodsCatalogTwoDao goodsCatalogTwo;

    @Override
    public List<GoodsCatalogTwo> listByGoodsCatalogOneId(Long goodsCatalogOneId) {
        if(goodsCatalogOneId != null){
            List<GoodsCatalogTwo> goodsCatalogTwos = goodsCatalogTwo.listByGoodsCatalogOneId(goodsCatalogOneId);
            return goodsCatalogTwos;
        }
        return null;
    }
}
