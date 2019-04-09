package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：
 * @Date: 2019/4/9
 * @Time: 17:38
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/listByGoodsCatalogOneId")
    public ResultVo<List<Goods>> listByGoodsCatalogOneId(Long goodsCatalogOneId){
        List<Goods> goods = goodsService.listByGoodsCatalogOneId(goodsCatalogOneId);
        return goods==null? ResultUtil.error(ErrorEnum.PARAM_IS_NULL):ResultUtil.success(goods);
    }

    @GetMapping(value = "/listByGoodsCatalogTwoId")
    public ResultVo<List<Goods>> listByGoodsCatalogTwoId(Long goodsCatalogTwoId){
        List<Goods> goods = goodsService.listByGoodsCatalogTwoId(goodsCatalogTwoId);
        return goods==null? ResultUtil.error(ErrorEnum.PARAM_IS_NULL):ResultUtil.success(goods);
    }

}
