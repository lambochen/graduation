package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.microscope.sniffer.behavior.UserGoodsBehaviorSniffer;
import com.chenlinghong.graduation.repository.domain.ShoppingCart;
import com.chenlinghong.graduation.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 购物车
 * @Author chenlinghong
 * @Date 2019/4/15 1:49
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/shopping/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SessionUtil sessionUtil;

    @Resource(name = "graduationUserGoodsBehaviorSniffer")
    private UserGoodsBehaviorSniffer userGoodsBehaviorSniffer;

    /**
     * 添加购物车
     *
     * @param goodsId
     * @param request
     * @return
     */
    @PostMapping(value = "/goods/{goodsId}")
    public ResultVo insert(@PathVariable(value = "goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId == null || goodsId <= 0) {
            log.error("ShoppingCartController#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        long userId = sessionUtil.getUserId(request);

        /**
         * 采集用户添加购物车行为
         */
        userGoodsBehaviorSniffer.addToShoppingCart(goodsId, request);

        ShoppingCart shoppingCart = new ShoppingCart(goodsId, null, userId, 1);
        int result = shoppingCartService.insert(shoppingCart);
        if (result == NumericConstant.ONE) {
            return ResultUtil.success();
        }
        /**
         * 添加失败
         */
        log.error("ShoppingCartController#insert: failed to insert shopping cart. goodsId={}, request={}, " +
                "result={}.", goodsId, request, result);
        throw new BusinessException(ErrorEnum.FAILED_TO_INSERT_SHOPPING_CART);
    }


    /**
     * 获取购物车列表
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/goods/list")
    public ResultVo listByUser(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                               HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(shoppingCartService.listByUser(userId, pageNo, pageSize));
    }

    /**
     * 批量删除
     *
     * @param idList
     * @param request
     * @return
     */
    @DeleteMapping(value = "/goods/list")
    public ResultVo deleteByIdList(@RequestParam(value = "idList") String idList, HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        String[] idArray = idList.split(",");
        List<Long> dataList = array2list(idArray);
        int result = shoppingCartService.deleteByIdList(dataList, userId);
        if (result != dataList.size()){
            log.error("ShoppingCartController#deleteByIdList: 删除失败. idList={}, userId={}.", idList, userId);
            throw new BusinessException(ErrorEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    private List<Long> array2list(String[] array) {
        List<Long> result = new LinkedList<>();
        for (String item : array) {
            result.add(Long.parseLong(item));
        }
        return result;
    }


}
