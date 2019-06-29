package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.microscope.sniffer.behavior.UserGoodsBehaviorSniffer;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import com.chenlinghong.graduation.service.GoodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description 商品订单
 * @Author chenlinghong
 * @Date 2019/4/15 2:53
 * @Version V1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/order")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService orderService;

    @Autowired
    private SessionUtil sessionUtil;

    @Resource(name = "graduationUserGoodsBehaviorSniffer")
    private UserGoodsBehaviorSniffer userGoodsBehaviorSniffer;

    /**
     * 新增订单
     *
     * @param goodsOrder
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping(value = "/order")
    public ResultVo insert(@Valid GoodsOrder goodsOrder, BindingResult bindingResult,
                           @RequestParam(value = "smsCode") String smsCode,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ErrorEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        log.info("GoodsController#insert: parameter info. goodsOrder={}, smsCode={}, request={}. ", smsCode, goodsOrder, request);
        if (StringUtils.isBlank(smsCode)) {
            // 参数为空
            log.error("GoodsController#insert: parameter is null. smsCode={}, goodsOrder={} request={}. ", smsCode, goodsOrder, request);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        boolean checkResult = sessionUtil.checkSmsCode(smsCode, request);
        if (checkResult == false) {
            log.info("GoodsController#insert: smsCode is timeout. smsCode={}, request={}. ", smsCode, request);
            throw new BusinessException(ErrorEnum.SMS_TIMEOUT);
        }
        long userId = sessionUtil.getUserId(request);
        goodsOrder.setUserId(userId);
        /**
         * TODO 采集用户购买行为
         */
        userGoodsBehaviorSniffer.purchase(goodsOrder);
        orderService.insert(goodsOrder);
        return ResultUtil.success();
    }

    /**
     * 根据ID获取订单详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/order/{id}")
    public ResultVo getById(@PathVariable(value = "id") Long id) {
        return ResultUtil.success(orderService.getById(id));
    }

    /**
     * 根据用户获取订单列表
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/order/user/list")
    public ResultVo listByUser(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                               HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(orderService.listByUser(userId, pageNo, pageSize));
    }

    /**
     * 根据商品iD 获取订单列表
     *
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/order/goods/{goodsId}")
    public ResultVo listByGoods(@PathVariable(value = "goodsId") Long goodsId,
                                @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize
    ) {
        return ResultUtil.success(orderService.listByGoods(goodsId, pageNo, pageSize));
    }


}
