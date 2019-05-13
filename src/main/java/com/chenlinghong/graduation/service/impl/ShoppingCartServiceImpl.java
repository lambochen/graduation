package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.ShoppingCartDao;
import com.chenlinghong.graduation.repository.domain.ShoppingCart;
import com.chenlinghong.graduation.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/4/15 1:44
 * @Version V1.0
 */
@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public int deleteByIdList(List<Long> idList, long userId) {
        if (idList == null || idList.size() <= 0) {
            log.error("ShoppingCartService#delelteByIdList: param is null");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return shoppingCartDao.deleteByIdList(idList, userId);
    }

    @Override
    public PageDto<ShoppingCart> listByUser(long userId, long pageNo, long pageSize) {
        List<ShoppingCart> shoppingCarts = shoppingCartDao.listByUser(userId, (pageNo - 1) * pageSize, pageSize);
        long total = shoppingCartDao.countByUser(userId);
        return new PageDto<>(shoppingCarts, pageNo, pageSize, total);
    }

    @Override
    public int insert(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            log.error("ShoppingCartService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 判断是否存在
        ShoppingCart dbCart = shoppingCartDao.getByUserGoods(shoppingCart.getGoodsId(), shoppingCart.getUserId());
        if (dbCart == null) {
            return shoppingCartDao.insert(shoppingCart);
        } else {
            shoppingCartDao.updateCount(dbCart.getId(), (dbCart.getCount() == null ? 1 : dbCart.getCount()) + 1);
            return 1;
        }
    }

    @Override
    public int deleteById(long id) {
        return shoppingCartDao.deleteById(id);
    }

    @Override
    public ShoppingCart getById(long id) {
        return shoppingCartDao.getById(id);
    }

    @Override
    public PageDto<ShoppingCart> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(ShoppingCart shoppingCart) {
        return 0;
    }

}
