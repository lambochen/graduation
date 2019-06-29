package com.chenlinghong.graduation.microscope.sniffer.ranking.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.microscope.sniffer.ranking.RecommendRankingGoodsSniffer;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.RecommendRankingGoodsService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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
    private RecommendRankingGoodsService rankingGoodsService;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        /**
         * 1、将DB中的ranking数据写入redis
         */
        initRedis();
    }

    @Override
    @Async(value = AsyncNameConstant.MICROSCOPE)
    public Boolean pushRanking(long goodsId) {
        /**
         * 写入redis
         */
        double redisResult = redisUtil.incrementScoreToRankginGoods(goodsId);
        /**
         * 写入数据库
         */
        int dbResult = rankingGoodsService.incrementRank(goodsId);
        /**
         * TODO 处理结果
         */
        return true;
    }


    /**
     * 将DB中的数据写入redis
     */
    private void initRedis() {
        long total = rankingGoodsService.count();
        PageDto<RecommendRankingGoods> rankingGoodsList = rankingGoodsService.listAll(NumericConstant.ONE, total);
        redisUtil.putRecommendRankingGoods(rankingGoodsList.getData());
    }


}
