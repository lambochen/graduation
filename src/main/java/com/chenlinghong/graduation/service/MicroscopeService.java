package com.chenlinghong.graduation.service;

/**
 * @Description 显微镜
 * @Author chenlinghong
 * @Date 2019/5/1 10:35
 * @Version V1.0
 */
public interface MicroscopeService {

    /**
     * 商品点击记录
     *
     * @param userId  用户ID
     * @param goodsId 商品ID
     */
    void clickGoods(long userId, long goodsId);

}
