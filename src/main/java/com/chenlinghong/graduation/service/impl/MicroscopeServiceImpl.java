package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.service.GoodsService;
import com.chenlinghong.graduation.service.MicroscopeService;
import com.chenlinghong.graduation.service.UserBehaviorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 显微镜Service实现类
 * @Author chenlinghong
 * @Date 2019/5/1 10:36
 * @Version V1.0
 */
@Slf4j
@Service
public class MicroscopeServiceImpl implements MicroscopeService {

    @Autowired
    private UserBehaviorService userBehaviorService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void clickGoods(long userId, long goodsId) {
        if (goodsService.isNotGoods(goodsId)) {
            // 商品不存在
            log.error("MicroscopeService#clickGoods: no goods. userId={}, goodsId={}.", userId, goodsId);
            return;
        }
        /**
         * TODO 刷新商品排行榜
         */
        if (userId <= 0) {
            // 用户未登录
            log.error("MicroscopeService#clickGoods: user not logged in. userId={}, goodsId={}.", userId, goodsId);
            return;
        }
        /**
         * 写入数据库，记录用户行为
         */
        int result = userBehaviorService.insert(goodsId, userId, UserBehaviorEnum.CLICK);
        /**
         * TODO 校验结果
         */
    }
}
