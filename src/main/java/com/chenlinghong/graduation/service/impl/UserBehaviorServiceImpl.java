package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.enums.UserBehaviorEnum;
import com.chenlinghong.graduation.exception.AsyncBusinessException;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.UserBehaviorDao;
import com.chenlinghong.graduation.repository.domain.UserBehavior;
import com.chenlinghong.graduation.service.UserBehaviorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户历史行为
 * @Author chenlinghong
 * @Date 2019/4/25 18:25
 * @Version V1.0
 */
@Slf4j
@Service
public class UserBehaviorServiceImpl implements UserBehaviorService {

    @Autowired
    private UserBehaviorDao behaviorDao;

    @Override
    public int insert(UserBehavior userBehavior) {
        if (userBehavior == null){
            log.error("UserBehaviorService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        // 校验用户行为
        int behavior = userBehavior.getBehavior();
        for (UserBehaviorEnum item : UserBehaviorEnum.values()){
            if (item.getCode() == behavior){
                // 有效的用户行为
                int result = behaviorDao.insert(userBehavior);
                if (result == 1){
                    // 插入成功
                    return result;
                }
                /**
                 * TODO 插入失败
                 */
                log.error("UserBehaviorService#insert: insert error. userBehavior={}, result={}. ", userBehavior, result);
                throw new AsyncBusinessException(ErrorEnum.ERROR_TO_INSERT_USER_BEHAVIOR);
            }
        }
        return 0;
    }

    @Override
    public int insert(long goodsId, long userId, int behavior) {
        UserBehavior userBehavior = new UserBehavior(goodsId, userId, behavior);
        return insert(userBehavior);
    }

    @Override
    public int insert(long goodsId, long userId, UserBehaviorEnum behaviorEnum) {
        if (behaviorEnum == null){
            log.error("UserBehaviorService#insert: param is null. goodsId={}, userId={}, behaviorEnum={}. ",
                    goodsId, userId, behaviorEnum);
            throw new AsyncBusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        UserBehavior userBehavior = new UserBehavior(goodsId, userId, behaviorEnum.getCode());
        return insert(userBehavior);
    }

    @Override
    public int deleteById(long id) {
        if (id <= 0){
            log.error("UserBehaviorService#deleteById: param is illegal. id={}. ", id);
            throw new AsyncBusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return behaviorDao.deleteById(id);
    }

    @Override
    public UserBehavior getById(long id) {
        if (id <= 0){
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
