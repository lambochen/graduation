package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.GoodsCommentDao;
import com.chenlinghong.graduation.repository.domain.GoodsComment;
import com.chenlinghong.graduation.service.GoodsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author chenlinghong
 * @Date 2019/4/15 12:18
 * @Version V1.0
 */
@Slf4j
@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

    @Autowired
    private GoodsCommentDao commentDao;

    @Override
    public int insert(GoodsComment goodsComment) {
        if (goodsComment == null) {
            log.error("GoodsCommentService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        /**
         * TODO 后期添加校验订单和用户
         */
        return commentDao.insert(goodsComment);
    }

    @Override
    public int deleteById(long id) {
        return commentDao.deleteById(id);
    }

    @Override
    public GoodsComment getById(long id) {
        return commentDao.getById(id);
    }

    @Override
    public PageDto<GoodsComment> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(GoodsComment goodsComment) {
        return 0;
    }

    @Override
    public int deleteByUser(long userId) {
        return commentDao.deleteByUser(userId);
    }

    @Override
    public int deleteById(long id, long userId) {
        /**
         * TODO 校验身份
         */
        return deleteById(id);
    }

    @Override
    public PageDto<GoodsComment> listByUser(long userId, long pageNo, long pageSize) {
        List<GoodsComment> commentList = commentDao.listByUser(userId, (pageNo - 1) * pageSize, pageSize);
        long total = commentDao.countByUser(userId);
        return new PageDto<>(commentList, pageNo, pageSize, total);
    }

    @Override
    public PageDto<GoodsComment> listByGoods(long goodsId, long pageNo, long pageSize) {
        List<GoodsComment> commentList = commentDao.listByGoods(goodsId, (pageNo - 1) * pageSize, pageSize);
        long total = commentDao.countByGoods(goodsId);
        return new PageDto<>(commentList, pageNo, pageSize, total);
    }
}
