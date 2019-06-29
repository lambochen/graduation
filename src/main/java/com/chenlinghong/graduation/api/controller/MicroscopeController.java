package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.service.MicroscopeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 显微镜
 * @Author chenlinghong
 * @Date 2019/4/25 23:25
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/microscope")
public class MicroscopeController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private MicroscopeService microscopeService;

    /**
     * 商品点击行为
     *
     * @param goodsId
     * @param request
     * @return
     */
    @PostMapping(value = "/click")
    public ResultVo click(@RequestParam(value = "goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId == null || goodsId <= 0) {
            log.error("MicroscopeController#click: param is illegal. goodsId={}", goodsId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserIdNoCheck(request);
        microscopeService.clickGoods(userId, goodsId);
        return ResultUtil.success();
    }

    /**
     * 上报用户行为
     *
     * @param goodsId  商品ID
     * @param behavior
     * @param request
     * @return
     */
    @PostMapping(value = "/report")
    public ResultVo report(@RequestParam(value = "goodsId") Long goodsId,
                           @RequestParam(value = "behavior") Integer behavior, HttpServletRequest request) {
        log.info("MicroscopeController#report: request log. goodsId=[{}], behavior=[{}]. ", goodsId, behavior);
        if (goodsId == null || goodsId <= 0) {
            log.error("MicroscopeController#click: param is illegal. goodsId={}", goodsId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserIdNoCheck(request);
        /**
         * 根据behavior获取枚举类
         */
        UserBehaviorEnum behaviorEnum = getBehaviorEnum(behavior);
        if (behaviorEnum == null) {
            /**
             * 参数非法
             */
            log.error("MicroscopeController#report: param is illegal. goodsId={}, behavior={}, " +
                    "userId={}.", goodsId, behavior, userId);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        microscopeService.report(userId, goodsId, behaviorEnum);
        return ResultUtil.success();
    }

    /**
     * 获取behavior枚举类
     *
     * @param behavior
     * @return
     */
    private UserBehaviorEnum getBehaviorEnum(@RequestParam(value = "behavior") Integer behavior) {
        UserBehaviorEnum result = null;
        for (UserBehaviorEnum behaviorEnum : UserBehaviorEnum.values()) {
            if (behaviorEnum.getCode() == behavior) {
                result = behaviorEnum;
                break;
            }
        }
        return result;
    }


}
