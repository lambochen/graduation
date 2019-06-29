package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.GoodsOrderDao;
import com.chenlinghong.graduation.repository.domain.GoodsOrder;
import com.chenlinghong.graduation.service.GoodsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/4/15 2:48
 * @Version V1.0
 */
@Slf4j
@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {

    @Autowired
    private GoodsOrderDao orderDao;

    @Override
    public int insert(GoodsOrder goodsOrder) {
        if (goodsOrder == null) {
            log.error("GoodsOrderService#insert: param is null");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return orderDao.insert(goodsOrder);
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public GoodsOrder getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public PageDto<GoodsOrder> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(GoodsOrder goodsOrder) {
        return 0;
    }

    @Override
    public PageDto<GoodsOrder> listByUser(long userId, long pageNo, long pageSize) {
        List<GoodsOrder> orderList = orderDao.listByUser(userId, (pageNo - 1) * pageSize, pageSize);
        long total = orderDao.countByUser(userId);
        return new PageDto<>(orderList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<GoodsOrder> listByGoods(long goodsId, long pageNo, long pageSize) {
        List<GoodsOrder> orderList = orderDao.listByGoods(goodsId, (pageNo - 1) * pageSize, pageSize);
        long total = orderDao.countByGoods(goodsId);
        return new PageDto<>(orderList, pageNo, pageSize, total);
    }
}
