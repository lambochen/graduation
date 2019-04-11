package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogOne;
import com.chenlinghong.graduation.service.GoodsCatalogOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：一级目录相关Controller
 * @Date: 2019/4/9
 * @Time: 21:52
 */

@RestController
@RequestMapping(value = "/goodscatalogone")
public class GoodsCatalogOneController {

    @Autowired
    private GoodsCatalogOneService goodsCatalogOneService;

    /**
     * 获取一级目录与二级目录所有列表
     *
     * @return
     */
    @GetMapping(value = "/listallgoodscatalogone")
    public ResultVo<List<GoodsCatalogOne>> listAllGoodsCatalogOne() {
        return ResultUtil.success(goodsCatalogOneService.listAllGoodsCatalogOne());
    }
}
