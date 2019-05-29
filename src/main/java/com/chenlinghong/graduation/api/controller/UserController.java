package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.api.vo.UserUpdateVo;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.domain.User;
import com.chenlinghong.graduation.service.UserBehaviorService;
import com.chenlinghong.graduation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserBehaviorService userBehaviorService;

    /**
     * 通过密码登录
     *
     * @param telephone
     * @param password
     * @param request
     * @return
     */
    @PostMapping(value = "/login/pwd")
    public ResultVo loginByPwd(@RequestParam(value = "telephone") String telephone,
                               @RequestParam(value = "password") String password, HttpServletRequest request) {
        log.info("UserController#loginByPwd: parameter info. telephone={}, password={}", telephone, password);
        if (StringUtils.isBlank(telephone) || StringUtils.isBlank(password)) {
            // 参数为空
            log.error("UserController#loginByPwd: param is null. telephone={}, password={}", telephone, password);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 写入手机号
        sessionUtil.putTelephone(telephone, request);
        return ResultUtil.success(userService.loginByPwd(telephone, password));
    }

    /**
     * 通过短信验证码登录，实现登录即注册
     *
     * @param smsCode
     * @param request
     * @return
     */
    @PostMapping(value = "/login/sms")
    public ResultVo loginBySms(@RequestParam(value = "smsCode") String smsCode, HttpServletRequest request) {
        log.info("UserController#loginBySms: parameter info. smsCode={}, request={}. ", smsCode, request);
        if (StringUtils.isBlank(smsCode)) {
            // 参数为空
            log.error("UserController#loginBySms: parameter is null. smsCode={}, request={}. ", smsCode, request);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        boolean checkResult = sessionUtil.checkSmsCode(smsCode, request);
        if (checkResult == true) {
            // 验证成功
            String telephone = SessionUtil.getTelephone(request);
            return ResultUtil.success(userService.loginBySms(telephone));
        }
        log.info("UserController#loginBySms: smsCode is timeout. smsCode={}, request={}. ", smsCode, request);
        return ResultUtil.error(ErrorEnum.SMS_TIMEOUT);
    }

    /**
     * 注冊用戶
     * TODO 已遗弃该接口
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public ResultVo register(@RequestBody User user) {
        log.info("UserController#register: param info. user={}", user);
        if (user == null) {
            log.error("UserController#register: param is null. user={}", user);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        userService.register(user);
        /**
         * 注册后直接返回空对象，强制要求用户登录一次
         */
        return ResultUtil.success();
    }

    /**
     * 获取用户行为历史
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/history")
    public ResultVo history(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                            HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(userBehaviorService.listByUser(userId, pageNo, pageSize));
    }


    /**
     * 更新用户基本信息
     *
     * @param userUpdateVo
     * @return
     */
    @PutMapping(value = "/user")
    public ResultVo update(@Valid UserUpdateVo userUpdateVo, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("UserController#update: param is illegal. userUpdateVo={}, bindingResult=(), request={}. ",
                    userUpdateVo, bindingResult, request);
            return ResultUtil.error(ErrorEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        User sessionUser = sessionUtil.getUser(request);
        if (sessionUser == null) {
            // 用户未登录
            log.error("UserController#update: user not logged in. userUpdateVo={}, request={}", userUpdateVo, request);
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        /**
         * 填充数据
         */
        padding(sessionUser, userUpdateVo);
        int result = userService.update(sessionUser);
        if (result == 1) {
            return ResultUtil.success();
        } else {
            log.error("UserController#update: failed update user. sessionUser={}", sessionUser);
            throw new BusinessException(ErrorEnum.UPDATE_USER_ERROR);
        }
    }


    /**
     * 修改密码
     *
     * @param smsCode  短信验证码
     * @param password 新密码
     * @param request
     * @return
     */
    @PutMapping(value = "/password")
    public ResultVo updatePassword(@RequestParam(value = "smsCode") String smsCode,
                                   @RequestParam(value = "password") String password, HttpServletRequest request) {
        if (StringUtils.isBlank(smsCode) || StringUtils.isBlank(password)) {
            log.error("UserController#updatePassword: param is null. smsCode={}, password={}, request={}"
                    , smsCode, password, request);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        if (sessionUtil.checkSmsCode(smsCode, request) == false) {
            // 短信验证码不正确
            log.error("UserController#updatePassword: smsCode is error. smsCode={}, password={}, request={}"
                    , smsCode, password, request);
            throw new BusinessException(ErrorEnum.SMS_ERROR);
        }
        long userId = sessionUtil.getUserId(request);
        userService.updatePassword(userId, password);
        return ResultUtil.success();
    }

    /**
     * 更新用户头像
     *
     * @param avatarUrl
     * @param request
     * @return
     */
    @PutMapping(value = "/avatar")
    public ResultVo updateAvatarUrl(@RequestParam(value = "avatarUrl") String avatarUrl, HttpServletRequest request) {
        if (StringUtils.isBlank(avatarUrl)) {
            log.error("UserController#updateAvatarUrl: param is null. avatarUrl={}, request={}", avatarUrl, request);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        long userId = sessionUtil.getUserId(request);
        userService.updateAvatarUrl(userId, avatarUrl);
        return ResultUtil.success();
    }


    /**
     * 填充数据
     *
     * @param target
     * @param updateVo
     */
    private void padding(User target, UserUpdateVo updateVo) {
        target.setNickName(updateVo.getNickName());
        target.setRealName(updateVo.getRealName());
        target.setGender(updateVo.getGender());
        target.setBirthday(updateVo.getBirthday());
        target.setProvince(updateVo.getProvince());
        target.setCity(updateVo.getCity());
        target.setPosition(updateVo.getPosition());
        target.setLatitude(updateVo.getLatitude());
        target.setLongitude(updateVo.getLongitude());
        target.setDescription(updateVo.getDescription());
    }

}
