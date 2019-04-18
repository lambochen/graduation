package com.chenlinghong.graduation.config;

import com.chenlinghong.graduation.GraduationApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @Description SpringBoot 配置类
 * @Author chenlinghong
 * @Date 2019/4/18 20:29
 * @Version V1.0
 */
@Configuration
public class SpringBootConfiguration extends SpringBootServletInitializer {

    /**
     * WAR包支持
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GraduationApplication.class);
    }
}
