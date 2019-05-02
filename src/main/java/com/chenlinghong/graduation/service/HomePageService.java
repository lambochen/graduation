package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.api.vo.HomePageVo;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 * @Description 首页
 * @Author chenlinghong
 * @Date 2019/4/29 9:24
 * @Version V1.0
 */
public interface HomePageService {

    /**
     * 获取主页数据
     *
     * @param userId 用户ID，可不填
     * @return
     */
    HomePageVo get(long userId) throws TasteException;

}
