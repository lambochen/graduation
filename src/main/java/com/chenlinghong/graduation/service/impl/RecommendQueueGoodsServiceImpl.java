package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.dao.RecommendQueueGoodsDao;
import com.chenlinghong.graduation.repository.domain.RecommendQueueGoods;
import com.chenlinghong.graduation.service.RecommendQueueGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品推荐队列, 可异步执行
 * @Author chenlinghong
 * @Date 2019/5/3 11:02
 * @Version V1.0
 */
@Slf4j
@Service
public class RecommendQueueGoodsServiceImpl implements RecommendQueueGoodsService {

    @Autowired
    private RecommendQueueGoodsDao recommendQueueGoodsDao;

    @Override
    public int insert(RecommendQueueGoods recommendQueueGoods) {
        if (recommendQueueGoods == null) {
            log.error("RecommendQueueGoodsService#insert: param is null.");
            return 0;
        }
        return recommendQueueGoodsDao.insert(recommendQueueGoods);
    }

    @Override
    public int deleteById(long id) {
        if (id <= 0) {
            log.error("RecommendQueueGoodsService#deleteById: param is illegal. id={}.", id);
            return 0;
        }
        return recommendQueueGoodsDao.deleteById(id);
    }

    @Override
    public RecommendQueueGoods getById(long id) {
        if (id <= 0) {
            log.error("RecommendQueueGoodsService#getById: param is illegal. id={}.", id);
            return null;
        }
        return null;
    }

    @Override
    public PageDto<RecommendQueueGoods> listAll(long pageNo, long pageSize) {
        if (pageNo <= 0 || pageSize < 0) {
            log.error("RecommendRankingGoodsService#listAll: param is illegal. pageNo={}, pageSize={}.",
                    pageNo, pageSize);
            return new PageDto<>();
        }
        List<RecommendQueueGoods> goodsList = recommendQueueGoodsDao.listAll((pageNo - 1) * pageSize, pageSize);
        int total = recommendQueueGoodsDao.count();
        return new PageDto<>(goodsList, pageNo, pageSize, total);
    }

    @Override
    public int update(RecommendQueueGoods recommendQueueGoods) {
        if (recommendQueueGoods == null) {
            log.error("RecommendQueueGoodsService#update: param is null.");
            return 0;
        }
        return recommendQueueGoodsDao.update(recommendQueueGoods);
    }
}
