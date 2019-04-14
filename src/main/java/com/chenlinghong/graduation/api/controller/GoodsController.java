package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 商品模块
 * @Author chenlinghong
 * @Date 2019/4/14 14:32
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsCatalogService catalogService;

    /**
     * 获取目录（获取一级目录）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/catalog", "/catalog/one"})
    public ResultVo listCatalog(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        if (pageNo < 0 || pageSize < 0) {
            // 参数错误
            log.error("GoodsController#listCatalog: param is illegal. pageNo={}, pageSize={}", pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return ResultUtil.success(catalogService.listAll(pageNo, pageSize));
    }

}
