package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.UserTagDao;
import com.chenlinghong.graduation.repository.domain.UserTag;
import com.chenlinghong.graduation.service.UserTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户标签
 * @Author chenlinghong
 * @Date 2019/4/27 18:02
 * @Version V1.0
 */
@Slf4j
@Service
public class UserTagServiceImpl implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    public int insert(UserTag userTag) {
        if (userTag == null) {
            log.error("UserTagService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userTagDao.insert(userTag);
        if (result != NumericConstant.ONE) {
            log.error("UserTagService#insert: failed to insert. result={}, userTag={}.", result, userTag);
            throw new BusinessException(ErrorEnum.INSERT_USER_TAG_ERROR);
        }
        return result;
    }

    @Override
    public int insert(List<UserTag> userTagList) {
        if (userTagList == null || userTagList.size() <= 0) {
            log.error("UserTagService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userTagDao.insertBatch(userTagList);
        if (result != userTagList.size()) {
            log.error("UserTagService#insert: failed to insert. result={}, userTagList={}.", result, userTagList);
            throw new BusinessException(ErrorEnum.INSERT_USER_TAG_ERROR);
        }
        return result;
    }


    @Override
    public int deleteById(long id) {
        if (id <= 0) {
            log.error("UserTagService#deleteById: param is illegal. id={}.", id);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return userTagDao.deleteById(id);
    }

    @Override
    public UserTag getById(long id) {
        if (id <= 0) {
            log.error("UserTagService#getById: param is illegal. id={}.", id);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        return userTagDao.getById(id);
    }

    @Override
    public PageDto<UserTag> listAll(long pageNo, long pageSize) {
        if (pageNo < 1 || pageSize < 0) {
            log.error("UserTagService#listAll: param is illegal. pageNo={}, pageSize={}.", pageNo, pageSize);
            throw new BusinessException(ErrorEnum.PARAM_ILLEGAL);
        }
        List<UserTag> userTagList = userTagDao.listAll((pageNo - 1) * pageSize, pageSize);
        long total = userTagDao.count();
        return new PageDto<>(userTagList, pageNo, pageSize, total);
    }


    @Override
    public PageDto<UserTag> listByUser(long userId) {
        List<UserTag> userTagList = userTagDao.listByUser(userId);
        return new PageDto<>(userTagList);
    }

    @Override
    public int update(UserTag userTag) {
        if (userTag == null) {
            log.error("UserTagService#update: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = userTagDao.update(userTag);
        if (result != NumericConstant.ONE) {
            log.error("UserTagService#update: failed to update. result={}, userTag={}.", result, userTag);
            throw new BusinessException(ErrorEnum.UPDATE_USER_TAG_ERROR);
        }
        return result;
    }


}
