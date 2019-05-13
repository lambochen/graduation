package com.chenlinghong.graduation.util;

import com.chenlinghong.graduation.constant.FilePathConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description 上传文件工具类
 * @Author chenlinghong
 * @Date 2018/12/15 22:42
 **/
@Slf4j
public class UploadUtil {

    /**
     * 批量上传文件
     *
     * @param files    文件数组
     * @param filePath 存储路径
     * @return
     */
    public static List<String> upload(MultipartFile[] files, final String filePath) {
        List<String> pictureList = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                String tmp = UploadUtil.upload(file, filePath);
                pictureList.add(tmp);
            }
        }
        return pictureList;
    }

    public static List<String> upload(List<MultipartFile> files, final String filePath) {
        List<String> pictureList = new ArrayList<>();
        for (MultipartFile file : files) {
            //保存文件
            String tmp = UploadUtil.upload(file, filePath);
            pictureList.add(tmp);
        }
        return pictureList;
    }

    /**
     * 上传文件至服务器,需要先创建相应的文件夹
     *
     * @param file     文件
     * @param filePath 文件存储子路径
     * @return 文件存储路径
     */
    public static String upload(MultipartFile file, final String filePath) {
        File targetFile;
        // 文件存储路径
        StringBuilder result = new StringBuilder(FilePathConstant.FILE_PATH);
        // 获取文件名 带后缀
        String fileName = file.getOriginalFilename();
        if (!StringUtils.isEmpty(fileName)) {
            //文件后缀
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //新的文件名
            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;
            // 更新文件存储路径
            result.append(filePath);

            //先判断文件是否存在
            String fileAdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            File file1 = new File("/data/app/graduation/file" + result.toString() + "/" + fileAdd);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            targetFile = new File(file1, fileName);
            try {
                file.transferTo(targetFile);

                // 更新文件存储路径
                result.append("/").append(fileAdd).append("/").append(fileName);
            } catch (IOException e) {
                log.error("UploadUtil upload,file.transferTo fail,msg={}", e);
                throw new BusinessException(ErrorEnum.FILE_HANDLE_ERROR);
            }
            return FilePathConstant.URL_PRE + result.toString();
        } else {
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
    }


    /**
     * 下载文件
     *
     * @param response 响应对象
     * @param fileName 文件名称
     */
    public static void download(HttpServletResponse response, String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }

        File file = new File(fileName);
        if (file.exists()) {
            // 默认每一次取1024B
            byte[] buffer = new byte[1024];
            FileInputStream fileInputStream;
            BufferedInputStream bufferedInputStream;
            try {

                // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                // 2.设置文件头：最后一个参数是设置下载文件名
                response.setHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/") + 1), "UTF-8"));

                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream();
                // 文件末尾标识
                int flag = -1;
                do {
                    flag = bufferedInputStream.read(buffer);
                    outputStream.write(buffer, 0, flag);
                } while (flag != -1);
            } catch (FileNotFoundException e) {
                log.error("UploadUtil.download FileNotFoundException,msg={}", e);
                throw new BusinessException(ErrorEnum.FILE_IS_NULL);
            } catch (IOException e) {
                log.error("UploadUtil.download IOException,msg={}", e);
                throw new BusinessException(ErrorEnum.FILE_STREAM_CREATE_ERROR);
            }
        } else {
            // 文件不存在
            log.error("UploadUtil.download IOException,msg={}", fileName);
            throw new BusinessException(ErrorEnum.FILE_IS_NULL);
        }
    }


}
