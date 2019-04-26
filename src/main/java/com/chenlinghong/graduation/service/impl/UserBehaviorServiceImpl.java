package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.microscope.sniffer.util.UserBehaviorUtil;
import com.chenlinghong.graduation.repository.dao.UserBehaviorDao;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.service.UserBehaviorService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 用户历史行为
 * @Author chenlinghong
 * @Date 2019/4/25 18:25
 * @Version V1.0
 */
@Slf4j(topic = "me")
@Service
public class UserBehaviorServiceImpl implements UserBehaviorService {

    @Autowired
    private UserBehaviorDao behaviorDao;

    @Override
    @Transactional
    public int insert(UserBehavior userBehavior) {
        if (userBehavior == null) {
            log.error("UserBehaviorService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 校验用户行为
        UserBehaviorEnum behaviorEnum = UserBehaviorUtil.getByBehavior(userBehavior.getBehavior());
        if (behaviorEnum == null){
            // 用户行为不存在
            log.error("UserBehaviorService#insert: param is illegal, userBehavior={}.", userBehavior);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        // 有效的用户行为
        int result = behaviorDao.insert(userBehavior);
        if (result == 1) {
            // 插入成功
            return result;
        }
        /**
         * TODO 插入失败
         */
        log.error("UserBehaviorService#insert: insert error. userBehavior={}, result={}. ", userBehavior, result);
        throw new AsyncBusinessException(ErrorEnum.ERROR_TO_INSERT_USER_BEHAVIOR);
    }

    @Override
    public int insert(List<UserBehavior> behaviorList) {
        if (behaviorList == null || behaviorList.size() <= 0) {
            log.error("UserBehaviorService#insert: param is null. behaviorList={}. ", behaviorList);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        /**
         * TODO 校验评分
         */
        int result = behaviorDao.batchInsertByUserBehavior(behaviorList);
        /**
         * TODO 校验结果
         */
    }

    @Override
    public int insert(long goodsId, long userId, int behavior) {
        UserBehavior userBehavior = new UserBehavior(goodsId, userId, behavior);
        return insert(userBehavior);
    }

    @Override
    public int insert(long goodsId, long userId, UserBehaviorEnum behaviorEnum) {
        if (behaviorEnum == null) {
            log.error("UserBehaviorService#insert: param is null. goodsId={}, userId={}, behaviorEnum={}. ",
                    goodsId, userId, behaviorEnum);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        UserBehavior userBehavior = new UserBehavior(goodsId, userId, behaviorEnum.getCode());
        return insert(userBehavior);
    }

    @Override
    @Transactional
    public int insert(long goodsId, long userId, int behavior, int frequency) {
        if (goodsId <= 0 || userId <= 0 || frequency <= 0) {
            log.error("UserBehaviorService#insert: param is illegal. goodsId={}, userId={}, behavior={}, " +
                    "frequency={}. ", goodsId, userId, behavior, frequency);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        for (UserBehaviorEnum item : UserBehaviorEnum.values()) {
            if (item.getCode() == behavior) {
                // 行为正确，写入DB
                List<Integer> frequencyList = Lists.newArrayList();
                for (int i = 1; i <= frequency; i++) {
                    frequencyList.add(i);
                }
                int result = behaviorDao.batchInsert(goodsId, userId, behavior, frequencyList);
                if (result == frequency) {
                    // 全部写入成功
                    return result;
                }
                /**
                 * TODO　写入错误, 抛出异常，触发事务机制，进行回滚
                 */
                log.error("UserBehaviorService#insert: failed to insert. goodsId={}, userId={}, behavior={}, " +
                        "frequencyList={}. result={}", goodsId, userId, behavior, frequencyList, result);
                throw new AsyncBusinessException(ErrorEnum.ERROR_TO_INSERT_USER_BEHAVIOR);
            }
        }
        return 0;
    }

    @Override
    public int insert(long goodsId, long userId, UserBehaviorEnum behaviorEnum, int frequency) {
        return insert(goodsId, userId, behaviorEnum.getCode(), frequency);
    }

    @Override
    public int deleteById(long id) {
        if (id <= 0) {
            log.error("UserBehaviorService#deleteById: param is illegal. id={}. ", id);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return behaviorDao.deleteById(id);
    }

    @Override
    public UserBehavior getById(long id) {
        if (id <= 0) {
            log.error("UserBehaviorService#getById: param is illegal. id={}. ", id);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return behaviorDao.getById(id);
    }

    @Override
    public PageDto<UserBehavior> listAll(long pageNo, long pageSize) {
        return null;
    }

    @Override
    public int update(UserBehavior userBehavior) {
        return 0;
    }

}
