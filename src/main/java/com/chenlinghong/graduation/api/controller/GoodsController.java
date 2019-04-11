package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.repository.domain.Goods;
import com.chenlinghong.graduation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:DxlinY
 * @Description：商品相关Controller
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
    @GetMapping(value = "/listbygoodscatalogoneid")
    public ResultVo<PageDto> listByGoodsCatalogOneId(long goodsCatalogOneId) {
        PageDto pageDto = goodsService.listByGoodsCatalogOneId(goodsCatalogOneId);
        return ResultUtil.success(pageDto);
    }

    /**
     * 根据二级目录id查找商品列表
     *
     * @param goodsCatalogTwoId
     * @return
     */
    @GetMapping(value = "/listbygoodscatalogtwoid")
    public ResultVo<PageDto> listByGoodsCatalogTwoId(long goodsCatalogTwoId) {
        PageDto pageDto = goodsService.listByGoodsCatalogTwoId(goodsCatalogTwoId);
        return ResultUtil.success(pageDto);
    }

    /**
     * 返回该id的商品详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getgoodsbyid")
    public ResultVo<Goods> getGoodsById(long id) {
        Goods goods = goodsService.getGoodsById(id);
        return ResultUtil.success(goods);
    }

}
