package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户基本信息
 * @Author chenlinghong
 * @Date 2019/4/3 15:31
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login/pwd")
    public ResultVo loginByPwd(String telephone, String password) {
        log.info("UserController#loginByPwd: parameter info. telephone={}, password={}", telephone, password);
        if (StringUtils.isBlank(telephone) || StringUtils.isBlank(password)) {
            // 参数为空
            log.error("UserController#loginByPwd: param is null. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return ResultUtil.success(userService.loginByPwd(telephone, password));
    }



}
