package com.chenlinghong.graduation.service.impl;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.constant.NumericConstant;
import com.chenlinghong.graduation.enums.ErrorEnum;
import com.chenlinghong.graduation.exception.BusinessException;
import com.chenlinghong.graduation.repository.dao.UserPreferenceDao;
import com.chenlinghong.graduation.repository.domain.UserPreference;
import com.chenlinghong.graduation.service.UserPreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户偏好数据Service实现类
 * @Author chenlinghong
 * @Date 2019/4/26 18:09
 * @Version V1.0
 */
@Slf4j
@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao preferenceDao;


    @Override
    public int insert(UserPreference userPreference) {
        if (userPreference == null){
            log.error("UserPreferenceService#insert: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        return preferenceDao.insert(userPreference);
    }

    @Override
    public int deleteById(long id) {
        return preferenceDao.deleteById(id);
    }

    @Override
    public UserPreference getById(long id) {
        return preferenceDao.getById(id);
    }

    @Override
    public UserPreference getByUserAndGoods(long userId, long goodsId) {
        return null;
    }

    @Override
    public PageDto<UserPreference> listAll(long pageNo, long pageSize) {
        List<UserPreference> preferenceList = preferenceDao.listAll((pageNo - 1) * pageSize, pageSize);
        int total = preferenceDao.count();
        return new PageDto<>(preferenceList, pageNo, pageSize, total);
    }

    @Override
    public int update(UserPreference userPreference) {
        if (userPreference == null){
            log.error("UserPreferenceService#update: param is null.");
            throw new BusinessException(ErrorEnum.PARAM_IS_NULL);
        }
        int result = 0;
        // 校验数据库是否存在该记录
        int count = preferenceDao.countByUserAndGoods(userPreference.getUserId(),userPreference.getGoodsId());
        if (count == NumericConstant.ONE){
            // 更新记录
            result = preferenceDao.update(userPreference);
            /**
             * TODO 校验结果
             */
        } else if (count == NumericConstant.ZERO){
            // 插入数据
            result = insert(userPreference);
            /**
             * TODO 校验结果
             */
        } else {
            /**
             * 数据错误
             */
        }
        return 0;
    }


}
