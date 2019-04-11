package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.service.GoodsCatalogOneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:DxlinY
 * @Description：一级目录测试单元
 * @Date: 2019/4/11
 * @Time: 14:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCatalogOneServiceImplTest {

    @Autowired
    private GoodsCatalogOneService goodsCatalogOneService;

    @Test
    public void listAllGoodsCatalogOne(){
        PageDto pageDto = goodsCatalogOneService.listAllGoodsCatalogOne();
        System.out.println(pageDto);
    }
}
