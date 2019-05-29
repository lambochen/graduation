package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.microscope.sniffer.behavior.UserGoodsBehaviorSniffer;
import com.chenlinghong.graduation.service.GoodsCatalogService;
import com.chenlinghong.graduation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private GoodsService goodsService;

    @Resource(name = "graduationUserGoodsBehaviorSniffer")
    private UserGoodsBehaviorSniffer userGoodsBehaviorSniffer;

    /**
     * 获取目录（获取一级目录）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/catalog", "/catalog/one"})
    public ResultVo listCatalog(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (pageNo < 0 || pageSize < 0) {
            // 参数错误
            log.error("GoodsController#listCatalog: param is illegal. pageNo={}, pageSize={}", pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return ResultUtil.success(catalogService.listAll(pageNo, pageSize));
    }

    /**
     * 根据一级目录ID获取二级目录列表
     *
     * @param catalogOneId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/catalog/two")
    public ResultVo listCatalogTwoByCatalogOne(
            @RequestParam(value = "catalogOneId") Integer catalogOneId,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (catalogOneId == null || catalogOneId <= 0 || pageNo < 0 || pageSize < 0) {
            // 参数错误
            log.error("GoodsController#listCatalogTwoByCatalogOne: param is illegal. " +
                    "catalogOneId={}, pageNo={}, pageSize={}", catalogOneId, pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return ResultUtil.success(catalogService.listByCatalogOne(catalogOneId, pageNo, pageSize));
    }

    /**
     * 根据ID获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/goods/{id}")
    public ResultVo getGoodsById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        if (id == null || id <= 0) {
            // 参数错误
            log.error("GoodsController#getGoodsById: param is illegal. id={}", id);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        /**
         * 采集用户点击记录，异步
         */
        userGoodsBehaviorSniffer.click(id, request);

        return ResultUtil.success(goodsService.getById(id));
    }

    /**
     * 根据一级目录ID获取商品信息列表
     *
     * @param catalogOneId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/goods/catalog/one/{catalogOneId}")
    public ResultVo listGoodsByCatalogOne(
            @PathVariable(value = "catalogOneId") Integer catalogOneId,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        if (catalogOneId == null || catalogOneId <= 0 || pageNo < 0 || pageSize < 0) {
            // 参数错误
            log.error("GoodsController#listGoodsByCatalogOne: param is illegal. " +
                    "catalogOneId={}, pageNo={}, pageSize={}", catalogOneId, pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return ResultUtil.success(
                goodsService.listByCatalogOne(catalogOneId, pageNo, pageSize));
    }

    /**
     * 根据二级目录ID获取商品信息列表
     *
     * @param catalogTwoId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/goods/catalog/two/{catalogTwoId}")
    public ResultVo listGoodsByCatalogTwo(
            @PathVariable(value = "catalogTwoId") Integer catalogTwoId,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        if (catalogTwoId == null || catalogTwoId <= 0 || pageNo < 0 || pageSize < 0) {
            // 参数错误
            log.error("GoodsController#listGoodsByCatalogTwo: param is illegal. " +
                    "catalogTwoId={}, pageNo={}, pageSize={}", catalogTwoId, pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return ResultUtil.success(
                goodsService.listByCatalogTwo(catalogTwoId, pageNo, pageSize));
    }


    /**
     * 通过商品名称获取
     *
     * @param goodsName
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/goods/name")
    public ResultVo searchByName(@RequestParam(value = "goodsName") String goodsName,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                                 HttpServletRequest request) {
        if (StringUtils.isBlank(goodsName)) {
            log.error("GoodsController#searchByName: param is null. goodsName={}, pageNo={}, pageSize={}. ", goodsName, pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        PageDto result = goodsService.searchByName(goodsName, pageNo, pageSize);
        /**
         * 采集用户搜索记录
         */
        userGoodsBehaviorSniffer.search(result, request);
        return ResultUtil.success(result);
    }

}
