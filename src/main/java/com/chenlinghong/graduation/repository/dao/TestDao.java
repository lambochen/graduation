package com.chenlinghong.graduation.repository.dao;

import com.chenlinghong.graduation.repository.domain.TestBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 测试
 * @Author chenlinghong
 * @Date 2019/3/13 20:21
 **/
public interface TestDao {

    int insert(TestBean testBean);

    int deleteById(int id);

    TestBean getById(int id);

    List<TestBean> listAll(@Param("offset") int offset, @Param("rows") int rows);
    int count();

    int update(TestBean testBean);
}
