package com.chenlinghong.graduation.microscope.sniffer.ranking.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.AsyncNameConstant;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.microscope.sniffer.ranking.RecommendRankingGoodsSniffer;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.GoodsService;
import com.chenlinghong.graduation.service.RecommendRankingGoodsService;
import com.chenlinghong.graduation.util.MyRedisUtil;
import com.chenlinghong.graduation.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @Override
    public Long getByGoods(long goodsId) {
        return redisUtil.rankToRankingGoods(goodsId);
    }

    @Override
    public PageDto<RecommendRankingGoods> topN(int n) {
        return range(NumericConstant.ONE, n);
    }

    @Override
    public PageDto<RecommendRankingGoods> range(int pageNo, int pageSize) {
        if (pageNo <= 0 || pageSize < 0) {
            log.error("RecommendRankingGoodsSniffer#range: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return new PageDto<>();
        }
        List<RecommendRankingGoods> rankingGoodsList =
                redisUtil.rangeWithScoresToRankingGoods((pageNo - 1) * pageSize, pageSize);
        return fillGoods(rankingGoodsList, pageNo, pageSize);
    }

    /**
     * TODO 将DB中的数据写入redis
     */
    private void initRedis() {

    }

    /**
     * 填充Goods数据
     *
     * @param rankingGoodsList
     * @return
     */
    private PageDto<RecommendRankingGoods> fillGoods(List<RecommendRankingGoods> rankingGoodsList,
                                                     int pageNo, int pageSize) {
        List<RecommendRankingGoods> dbData = rankingGoodsService.listByGoodsList(rankingGoodsList);
        int total = rankingGoodsService.count();
        return new PageDto<>(dbData, pageNo, pageSize, total);
    }
}
