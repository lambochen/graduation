package com.chenlinghong.graduation.api.controller;

import com.alibaba.fastjson.JSON;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.repository.domain.UserTag;
import com.chenlinghong.graduation.service.UserTagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 用户标签
 * @Author chenlinghong
 * @Date 2019/4/27 18:01
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/user/tag")
public class UserTagController {

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private SessionUtil sessionUtil;

    /**
     * 批量新增
     *
     * @param userTagList
     * @param request
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVo insertBatch(@RequestParam(value = "userTagList") String userTagList,
                                HttpServletRequest request) {
        if (StringUtils.isBlank(userTagList)) {
            log.error("UserTagController#insertBatch: param is null. userTagList={}, request={}.",
                    userTagList, request);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        long userId = sessionUtil.getUserIdNoCheck(request);
        List<UserTag> userTagList1 = JSON.parseArray(userTagList, UserTag.class);
        /**
         * 填充UserId
         */
        for (UserTag item : userTagList1) {
            item.setUserId(userId);
        }
        int result = userTagService.insert(userTagList1);
        /**
         * TODO 校验结果
         */
        return ResultUtil.success();
    }

    /**
     * 获取用户所有标签
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/list")
    public ResultVo listByUser(HttpServletRequest request) {
        long userId = sessionUtil.getUserIdNoCheck(request);
        return ResultUtil.success(userTagService.listByUser(userId));
    }

}
