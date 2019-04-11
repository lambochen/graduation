package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.service.GoodsCatalogTwoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:DxlinY
 * @Description：二级目录测试单元
 * @Date: 2019/4/11
 * @Time: 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCatalogTwoServiceImplTest {

    @Autowired
    private GoodsCatalogTwoService goodsCatalogTwoService;

    @Test
    public void listByGoodsCatalogOneId(){
        PageDto pageDto = goodsCatalogTwoService.listByGoodsCatalogOneId(1L);
        System.out.println(pageDto);
    }
}
