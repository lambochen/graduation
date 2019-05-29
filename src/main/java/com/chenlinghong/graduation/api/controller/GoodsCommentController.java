package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.microscope.sniffer.behavior.UserGoodsBehaviorSniffer;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.service.GoodsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @Description 商品订单评论
 * @Author chenlinghong
 * @Date 2019/4/15 12:24
 * @Version V1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/comment")
public class GoodsCommentController {

    @Autowired
    private GoodsCommentService commentService;

    @Autowired
    private SessionUtil sessionUtil;

    @Resource(name = "graduationUserGoodsBehaviorSniffer")
    private UserGoodsBehaviorSniffer userGoodsBehaviorSniffer;

    /**
     * 新增评论
     * @param goodsComment  商品评论
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping(value = "/comment")
    public ResultVo insert(@Valid GoodsComment goodsComment, BindingResult bindingResult,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("GoodsCommentController#insert: param is illegal. goodsComment={}, request={}.", goodsComment, request);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        long userId = sessionUtil.getUserId(request);
        goodsComment.setUserId(userId);
        /**
         * 采集用户评论行为
         */
        userGoodsBehaviorSniffer.comment(goodsComment);


        int result = commentService.insert(goodsComment);
        if (result == 1){
            return ResultUtil.success();
        }
        /**
         * 插入数据错误
         */
        log.error("GoodsCommentController#insert: insert error. goodsComment={}, request={}. ", goodsComment, request);
        throw new BusinessException(ErrorEnum.COMMENT_INSERT_ERROR);
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping(value = "/comment/{id}")
    public ResultVo deleteById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        int result = commentService.deleteById(id, userId);
        /**
         * TODO 校验结果
         */
        return ResultUtil.success();
    }


    /**
     * 根据用户获取
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/comment/user/list")
    public ResultVo listByUser(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize,
                               HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(commentService.listByUser(userId, pageNo, pageSize));
    }

    /**
     * 根据商品获取
     *
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/comment/goods/{goodsId}")
    public ResultVo listByGoods(@PathVariable(value = "goodsId") Long goodsId,
                                @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) {
        return ResultUtil.success(commentService.listByGoods(goodsId, pageNo, pageSize));
    }
}
