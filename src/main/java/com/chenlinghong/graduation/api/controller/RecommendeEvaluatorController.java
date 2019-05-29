package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.enums.RecommendTypeEnum;
import com.chenlinghong.graduation.service.RecommendeEvaluatorService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 推荐评估
 * @Author chenlinghong
 * @Date 2019/5/30 3:27
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "/evalutor")
public class RecommendeEvaluatorController {

    @Autowired
    private RecommendeEvaluatorService recommendeEvaluatorService;

    /**
     * 分页获取。并且触发
     *
     * @return
     */
    @GetMapping(value = "/evalutor")
    public ResultVo listAll(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) throws TasteException {
        return ResultUtil.success(recommendeEvaluatorService.listAll(pageNo, pageSize));
    }

    /**
     * 根据类型获取
     * @param typeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/evalutor/type/{typeId}")
    public ResultVo listByType(
            @PathVariable(value = "typeId") Integer typeId,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Long pageSize) throws TasteException {
        RecommendTypeEnum typeEnum = RecommendController.getByCode(typeId);
        return ResultUtil.success(recommendeEvaluatorService.listByType(typeEnum, pageNo, pageSize));
    }
}
