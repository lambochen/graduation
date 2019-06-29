package com.chenlinghong.graduation.sms;

import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description HTTP请求工具
 * @Author chenlinghong
 * @Date 2019/1/24 16:37
 **/
@Slf4j
public class HttpUtil {

    /**
     * 连接超时时间
     */
    public static final Integer connectTimeout = 5000;

    /**
     * 读取超时时间
     */
    public static final Integer readTimeout = 20000;

    /**
     * 请求类型名称
     */
    public static final String contentTypeName = "Content-Type";

    /**
     * 请求类型值
     */
    public static final String contentTypeValue = "application/x-www-form-urlencoded";

    /**
     * 字符集编码
     */
    public static final String charsetName = "UTF-8";

    /**
     * 创建请求参数 短信验证码
     * @return
     */
    public static String createCommonParam(){
        /**
         * 时间戳
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());

        /**
         * 签名
         */
        String sign = DigestUtils.md5Hex(SmsConfiguration.ACCOUNT_SID + SmsConfiguration.AUTH_TOKEN + timestamp);

        String result = "&timestamp=" + timestamp + "&sig=" + sign + "&respDataType=" + SmsConfiguration.RESP_DATA_TYPE;

        return result;
    }

    /**
     * post请求
     * @param url   功能和操作
     * @param body  数据
     * @return
     */
    public static String post(String url, String body) {
        StringBuilder result = new StringBuilder("");
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        URL realUrl = null;
        URLConnection urlConnection = null;
        try {
            realUrl = new URL(url);
        } catch (MalformedURLException e) {
            log.error("create url fail, url={}, body={},exception={}",url,body,e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        try {
             urlConnection = realUrl.openConnection();
        } catch (IOException e) {
            log.error("failed to open connection, url={}, body={},exception={}",url,body,e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        /**
         * 设置连接参数
         */
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setConnectTimeout(connectTimeout);
        urlConnection.setReadTimeout(readTimeout);
        urlConnection.setRequestProperty(contentTypeName,contentTypeValue);
        /**
         * 提交数据
         */
        try {
            outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream(), charsetName);
            outputStreamWriter.write(body);
            outputStreamWriter.flush();
        } catch (IOException e) {
            log.error("outputStream failed, url={}, body={},exception={}",url,body,e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        /**
         * 读取返回数据
         */
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),charsetName));
        } catch (IOException e) {
            log.error("bufferedReader failed, url={}, body={},exception={}",url,body,e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        String line = "";
        // 读取第一行不加换行符
        Boolean firstLine = true;
        try {
            while ((line = bufferedReader.readLine()) != null){
                if (firstLine){
                    firstLine = false;
                } else {
                    result.append(System.lineSeparator());
                }
                result.append(line);
            }
        } catch (IOException e){
            log.error("bufferedReader.readLine() failed, url={}, body={},exception={}",url,body,e);
            throw new BusinessException(ErrorEnum.SMS_SEND_ERROR);
        }
        return result.toString();
    }
}
