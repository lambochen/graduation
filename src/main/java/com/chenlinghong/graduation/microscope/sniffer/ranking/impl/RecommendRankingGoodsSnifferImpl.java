package com.chenlinghong.graduation.microscope.sniffer.ranking.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.microscope.sniffer.ranking.RecommendRankingGoodsSniffer;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.GoodsService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/5/3 11:26
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommendRankingGoodsSnifferImpl implements RecommendRankingGoodsSniffer {

    @Autowired
    private MyRedisUtil redisUtil;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Autowired
    private GoodsService goodsService;

    @Override
    public boolean pushRanking(long goodsId) {
        return false;
    }

    @Override
    public RecommendRankingGoods getByGoods(long goodsId) {
        return null;
    }

    @Override
    public PageDto<RecommendRankingGoods> topN(int n) {
        return null;
    }
}
