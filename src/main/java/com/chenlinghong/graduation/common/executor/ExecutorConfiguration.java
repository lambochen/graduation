package com.chenlinghong.graduation.common.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 线程池配置
 * @Author chenlinghong
 * @Date 2019/4/7 9:37
 * @Version V1.0
 */
@Configuration
@EnableAsync
@Slf4j
@ComponentScan("com.chenlinghong.graduation")
public class ExecutorConfiguration {

    /**
     * 核心线程数
     */
    @Value("${executor.core_pool_size}")
    private int corePoolSize;

    /**
     * 最大线程数
     */
    @Value("${executor.max_pool_size}")
    private int maxPoolSize;

    /**
     * 队列容量大小
     */
    @Value("${executor.queue_capacity}")
    private int queueCapacity;

    /**
     * 线程池中线程的名称前缀
     */
    @Value("${executor.thread_name_prefix}")
    private String threadNamePrefix;

    /**
     * 线程池中线程的名称前缀 显微镜
     */
    @Value("${executor.thread_name_prefix_microscope}")
    private String threadNamePrefixMicroscope;

    /**
     * 线程池中线程的名称前缀 执行器
     */
    @Value("${executor.thread_name_prefix_scheduler}")
    private String threadNamePrefixScheduler;


    /**
     * 保持线程空闲时间
     */
    @Value("${executor.keep_alive_seconds}")
    private int keepAliveSeconds;


    /**
     * 基础业务逻辑
     *
     * @return
     */
    @Bean
    public Executor asyncServiceExecutor() {
        log.info("ExecutorConfiguration#asyncServiceExecutor start...");
        return getDefaultExecutor();
    }

    /**
     * Redis交互
     *
     * @return
     */
    @Bean
    public Executor asyncRedisExecutor() {
        log.info("ExecutorConfiguration#asyncRedisExecutor start...");
        return getDefaultExecutor();
    }

    /**
     * Session交互
     *
     * @return
     */
    @Bean
    public Executor asyncSessionExecutor() {
        log.info("ExecutorConfiguration#asyncSessionExecutor start...");
        return getDefaultExecutor();
    }

    /**
     * 显微镜
     *
     * @return
     */
    @Bean
    public Executor asyncMicroscopeExecutor() {
        log.info("ExecutorConfiguration#asyncMicroscopeExecutor start...");
        return getMicroscopeExecutor();
    }

    @Bean
    public Executor asyncSchedulerExecutor(){
        log.info("ExecutorConfiguration#asyncSchedulerExecutor start...");
        return getDefaultExecutor();
    }

    @Bean
    public Executor asyncEvalutorExecutor(){
        log.info("ExecutorConfiguration#asyncEvalutorExecutor start...");
        return getDefaultExecutor();
    }


    /**
     * 线程池--拒绝策略RejectedExecutionHandler
     *
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */

    /**
     * 获取默认执行器
     *
     * @return
     */
    private synchronized Executor getDefaultExecutor() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 获取显微镜执行器
     *
     * @return
     */
    private synchronized Executor getMicroscopeExecutor() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefixMicroscope);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

}
