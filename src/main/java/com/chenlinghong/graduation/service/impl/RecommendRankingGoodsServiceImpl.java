package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.repository.dao.RecommendRankingGoodsDao;
import com.chenlinghong.graduation.repository.domain.RecommendRankingGoods;
import com.chenlinghong.graduation.service.RecommendRankingGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 推荐商品排行榜，可异步执行
 * @Author chenlinghong
 * @Date 2019/5/3 10:38
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommendRankingGoodsServiceImpl implements RecommendRankingGoodsService {

    @Autowired
    private RecommendRankingGoodsDao recommendRankingGoodsDao;

    @Override
    public int insert(RecommendRankingGoods recommendRankingGoods) {
        if (recommendRankingGoods == null) {
            log.error("RecommendRankingGoodsService#insert: param is null.");
            return 0;
        }
        /**
         * 校验是否存在该商品
         */
        RecommendRankingGoods dbGoods = recommendRankingGoodsDao.getByGoods(recommendRankingGoods.getGoodsId());
        if (dbGoods == null) {
            // 不存在该商品，插入
            int result = recommendRankingGoodsDao.insert(recommendRankingGoods);
            /**
             * TODO 校验结果
             */
            return result;
        }
        /**
         * 存在该商品，更新rank
         */
        int rank = (dbGoods.getRanking() == null ? 0 : dbGoods.getRanking()) + recommendRankingGoods.getRanking();
        recommendRankingGoods.setRanking(rank);
        recommendRankingGoods.setId(dbGoods.getId());
        recommendRankingGoodsDao.update(recommendRankingGoods);
        return 0;
    }

    @Override
    public int deleteById(long id) {
        if (id <= 0) {
            log.error("RecommendRankingGoodsService#deleteById: param is illegal. id={}.", id);
            return 0;
        }
        return recommendRankingGoodsDao.deleteById(id);
    }

    @Override
    public RecommendRankingGoods getById(long id) {
        if (id <= 0) {
            log.error("RecommendRankingGoodsService#getById: param is illegal. id={}.", id);
            return null;
        }
        return recommendRankingGoodsDao.getById(id);
    }

    @Override
    public PageDto<RecommendRankingGoods> listAll(long pageNo, long pageSize) {
        if (pageNo <= 0 || pageSize < 0) {
            log.error("RecommendRankingGoodsService#listAll: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return new PageDto<>();
        }
        List<RecommendRankingGoods> rankingGoodsList =
                recommendRankingGoodsDao.listAll((pageNo - 1) * pageSize, pageSize);
        long total = recommendRankingGoodsDao.count();
        return new PageDto<>(rankingGoodsList, pageNo, pageSize, total);
    }

    @Override
    public int update(RecommendRankingGoods recommendRankingGoods) {
        if (recommendRankingGoods == null) {
            log.error("RecommendRankingGoodsService#update: param is null.");
            return 0;
        }
        return recommendRankingGoodsDao.update(recommendRankingGoods);
    }

    @Override
    public int incrementRank(long goodsId) {
        return incrementRank(goodsId, NumericConstant.ONE);
    }

    @Override
    public int incrementRank(long goodsId, int rank) {
        /**
         * 校验数据库是否存在goods
         */
        RecommendRankingGoods rankingGoods = new RecommendRankingGoods();
        rankingGoods.setGoodsId(goodsId);
        rankingGoods.setRanking(rank);
        return insert(rankingGoods);
    }

    @Override
    public List<RecommendRankingGoods> listByGoodsList(List<RecommendRankingGoods> rankingGoodsList) {
        return recommendRankingGoodsDao.listByGoodsList(rankingGoodsList);
    }

    @Override
    public long count() {
        return recommendRankingGoodsDao.count();
    }
}
