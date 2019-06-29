package com.chenlinghong.graduation.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * @Description 数据源配置类
 * @Author chenlinghong
 * @Date 2019/3/13 19:40
 **/
@Configuration
// 配置mybatis mapper扫描路径
@MapperScan(value = {"com.chenlinghong.graduation.repository.dao", "com.chenlinghong.graduation.recommender"})
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.database-name}")
    private String jdbcDatabaseName;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createComboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(jdbcDriver);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        comboPooledDataSource.setUser(jdbcUsername);
        comboPooledDataSource.setPassword(jdbcPassword);
        //关闭连接后不自动commit
        comboPooledDataSource.setAutoCommitOnClose(false);
        return comboPooledDataSource;
    }

    /**
     * MySQL datasource
     * @return
     */
    // @Bean(name = "mysqlDataSource")
    // public MysqlDataSource createMysqlDataSource() {
    //     MysqlDataSource mysqlDataSource = new MysqlDataSource();
    //     mysqlDataSource.setServerName("localhost");
    //     mysqlDataSource.setUser(jdbcUsername);
    //     mysqlDataSource.setPassword(jdbcPassword);
    //     mysqlDataSource.setDatabaseName(jdbcDatabaseName);
    //     mysqlDataSource.setUrl(jdbcUrl);
    //     return mysqlDataSource;
    // }


}
