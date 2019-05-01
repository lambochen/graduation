package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.service.MicroscopeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResultVo click(long goodsId, HttpServletRequest request) {
        if (goodsId <= 0) {
            log.error("MicroscopeController#click: param is illegal. goodsId={}", goodsId);
            return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
        }
        long userId = sessionUtil.getUserIdNoCheck(request);
        microscopeService.clickGoods(userId, goodsId);
        return ResultUtil.success();
    }

}
