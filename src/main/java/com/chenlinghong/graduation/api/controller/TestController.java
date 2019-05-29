package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试
 * @Author chenlinghong
 * @Date 2019/3/13 20:59
 **/
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 分页获取所有
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/all")
    public ResultVo listAll(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize){
        return ResultUtil.success(testService.listAll(pageNo, pageSize));
    }

}
