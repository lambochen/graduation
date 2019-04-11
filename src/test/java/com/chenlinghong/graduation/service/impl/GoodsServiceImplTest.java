package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:DxlinY
 * @Description：商品模块测试单元
 * @Date: 2019/4/11
 * @Time: 14:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void listByGoodsCatalogOneId() {
        PageDto pageDto = goodsService.listByGoodsCatalogOneId(1L);
        System.out.println(pageDto);
    }

    @Test
    public void listByGoodsCatalogTwoId() {
        PageDto pageDto = goodsService.listByGoodsCatalogTwoId(1L);
        System.out.println(pageDto);
    }

    @Test
    public void getGoodsById() {
        Goods goods = goodsService.getGoodsById(1L);
        System.out.println(goods);
    }
}
