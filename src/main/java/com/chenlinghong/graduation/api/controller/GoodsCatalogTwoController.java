package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.repository.domain.GoodsCatalogTwo;
import com.chenlinghong.graduation.service.GoodsCatalogTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:DxlinY
 * @Description：二级目录相关Controller
 * @Date: 2019/4/9
 * @Time: 15:31
 */
@RestController
@RequestMapping(value = "/goodscatalogtwo")
public class GoodsCatalogTwoController {

    @Autowired
    private GoodsCatalogTwoService goodsCatalogTwoService;

    /**
     * 根据一级目录查找二级二级目录列表
     *
     * @param goodsCatalogOneId
     * @return
     */
    @GetMapping(value = "/listbygoodscatalogoneid")
    public ResultVo<PageDto<GoodsCatalogTwo>> listByGoodsCatalogOneId(long goodsCatalogOneId) {
        PageDto pageDto = goodsCatalogTwoService.listByGoodsCatalogOneId(goodsCatalogOneId);
        return ResultUtil.success(pageDto);
    }
}
