package com.chenlinghong.graduation.recommender.cf;

import com.chenlinghong.graduation.recommender.Recommender;
import com.chenlinghong.graduation.recommender.data.model.GraduationMysqlDataModel;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description 基于用户的协同过滤推荐
 * @Author chenlinghong
 * @Date 2019/4/27 21:14
 * @Version V1.0
 */
public class UserBasedCFRecommender implements Recommender {

    /**
     * MySQL DataModel
     */
    private GraduationMysqlDataModel mysqlDataModel;

    /**
     * 数据源
     */
    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        mysqlDataModel = new GraduationMysqlDataModel(dataSource);
    }

    


}
