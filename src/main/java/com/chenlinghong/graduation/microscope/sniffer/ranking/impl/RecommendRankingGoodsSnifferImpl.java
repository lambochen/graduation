package com.chenlinghong.graduation.microscope.sniffer.ranking.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.microscope.sniffer.ranking.RecommendRankingGoodsSniffer;
import com.chenlinghong.graduation.microscope.sniffer.ranking.dto.RecommendRankingGoodsDto;
import lombok.extern.slf4j.Slf4j;
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

    

    @Override
    public boolean pushRanking(long goodsId) {
        return false;
    }

    @Override
    public RecommendRankingGoodsDto getByGoods(long goodsId) {
        return null;
    }

    @Override
    public PageDto<RecommendRankingGoodsDto> topN(int n) {
        return null;
    }
}
