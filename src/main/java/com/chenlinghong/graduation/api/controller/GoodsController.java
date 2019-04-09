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

    /**
     * 根据一级目录id查找商品列表
     *
     * @param goodsCatalogOneId
     * @return
     */
    @GetMapping(value = "/listByGoodsCatalogOneId")
    public ResultVo<List<Goods>> listByGoodsCatalogOneId(Long goodsCatalogOneId) {
        List<Goods> goods = goodsService.listByGoodsCatalogOneId(goodsCatalogOneId);
        return goods == null ? ResultUtil.error(ErrorEnum.PARAM_IS_NULL) : ResultUtil.success(goods);
    }

    /**
     * 根据二级目录id查找商品列表
     *
     * @param goodsCatalogTwoId
     * @return
     */
    @GetMapping(value = "/listByGoodsCatalogTwoId")
    public ResultVo<List<Goods>> listByGoodsCatalogTwoId(Long goodsCatalogTwoId) {
        List<Goods> goods = goodsService.listByGoodsCatalogTwoId(goodsCatalogTwoId);
        return goods == null ? ResultUtil.error(ErrorEnum.PARAM_IS_NULL) : ResultUtil.success(goods);
    }

    /**
     * 返回该id的商品详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getGoodsById")
    public ResultVo<Goods> getGoodsById(Long id) {
        Goods goods = goodsService.getGoodsById(id);
        return goods == null?ResultUtil.error(ErrorEnum.PARAM_IS_NULL):ResultUtil.success(goods);
    }

}
