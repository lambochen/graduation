package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 商户信息
 * @Author chenlinghong
 * @Date 2019/4/15 1:22
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/business/{id}")
    public ResultVo getById(@PathVariable(value = "id") Long id) {
        return ResultUtil.success(businessService.getById(id));
    }
}
