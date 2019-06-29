package com.chenlinghong.graduation.api.controller;

import com.chenlinghong.graduation.api.util.SessionUtil;
import com.chenlinghong.graduation.common.ResultUtil;
import com.chenlinghong.graduation.common.ResultVo;
import com.chenlinghong.graduation.constant.FilePathConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.ModularEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 文件上传下载
 * @Author chenlinghong
 * @Date 2018/12/26 16:04
 **/
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private SessionUtil sessionUtil;

    /**
     * 上传文件测试，可批量上传
     *
     * @param files 文件数组名
     * @return 文件存储访问URL
     */
    @PostMapping(value = "/test")
    public ResultVo upload(@RequestParam(value = "files") MultipartFile[] files) {
        List<String> pictureList = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                String tmp = UploadUtil.upload(file, FilePathConstant.TEST);
                pictureList.add(FilePathConstant.URL_PRE + tmp);
            }
        }
        return ResultUtil.success(pictureList);
    }

    /**
     * 上传文件
     *
     * @param files   文件数组
     * @param modular 模块
     * @return
     */
    @PostMapping("/upload")
    public ResultVo upload(@RequestParam(value = "files") MultipartFile[] files,
                           @RequestParam(value = "modular") Integer modular,
                           HttpServletRequest request) {
        if (sessionUtil.isAliveUser(request) == false) {
            log.error("FileController#upload: user not logged in.");
            throw new BusinessException(ErrorEnum.NO_USER);
        }
        if (files == null || files.length <= 0) {
            log.error("FileController.upload(file,modular): param is null. files={}, modular={}", files, modular);
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        for (ModularEnum item : ModularEnum.values()) {
            if (item.getCode() == modular) {
                List<String> list = UploadUtil.upload(files, item.getFilePath());
                return ResultUtil.success(list);
            }
        }
        return ResultUtil.error(ErrorEnum.PARAM_ILLEGAL);
    }


    /**
     * 文件下载
     *
     * @param file     文件名称
     * @param response 响应对象
     * @return 响应结果
     */
    @GetMapping(value = "/download")
    public ResultVo download(String file, HttpServletResponse response) {
        if (StringUtils.isBlank(file)) {
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        UploadUtil.download(response, file);
        return ResultUtil.success();
    }
}
