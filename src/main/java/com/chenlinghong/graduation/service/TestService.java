package com.chenlinghong.graduation.service;

import com.chenlinghong.graduation.common.PageDto;
import com.chenlinghong.graduation.repository.domain.TestBean;

/**
 * @Description 测试
 * @Author chenlinghong
 * @Date 2019/3/13 20:19
 **/
public interface TestService {

    /**
     * 分页获取所有列表
     *
     * @param pageNo   第几页
     * @param pageSize 每页条数
     * @return
     */
    PageDto<TestBean> listAll(long pageNo, long pageSize);

}
