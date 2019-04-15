package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.service.GoodsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 新增评论
     *
     * @param goodsId
     * @param content
     * @param imgOne
     * @param imgTwo
     * @param imgThree
     * @param request
     * @return
     */
    @PostMapping(value = "/comment")
    public ResultVo insert(@RequestParam(value = "goodsId") long goodsId,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "imgOne", required = false) String imgOne,
                           @RequestParam(value = "imgTwo", required = false) String imgTwo,
                           @RequestParam(value = "imgThree", required = false) String imgThree,
                           HttpServletRequest request) {
        if (goodsId <= 0 || StringUtils.isBlank(content)) {
            log.error("GoodsCommentController#insert: param is null. goodsId={}, content={}, " +
                    "imgOne={}, imgTwo={}, imgThree={}.", goodsId, content, imgOne, imgTwo, imgThree);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        long userId = sessionUtil.getUserId(request);
        GoodsComment goodsComment = new GoodsComment();
        goodsComment.setUserId(userId);
        goodsComment.setGoodsId(goodsId);
        goodsComment.setContent(content);
        goodsComment.setImgOne(imgOne);
        goodsComment.setImgTwo(imgTwo);
        goodsComment.setImgThree(imgThree);
        commentService.insert(goodsComment);
        return ResultUtil.success();
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping(value = "/comment/{id}")
    public ResultVo deleteById(@PathVariable(value = "id") long id, HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        int result = commentService.deleteById(id, userId);
        /**
         * TODO 校验结果
         */
        return ResultUtil.success();
    }


    /**
     * 根据用户获取
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping(value = "/comment/user/list")
    public ResultVo listByUser(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                               HttpServletRequest request) {
        long userId = sessionUtil.getUserId(request);
        return ResultUtil.success(commentService.listByUser(userId, pageNo, pageSize));
    }

    /**
     * 根据商品获取
     * @param goodsId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/comment/goods/{goodsId}")
    public ResultVo listByGoods(@PathVariable(value = "goodsId") long goodsId,
                                @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
        return ResultUtil.success(commentService.listByGoods(goodsId, pageNo, pageSize));
    }
}
