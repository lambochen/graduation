package com.chenlinghong.graduation.constant;

/**
 * @Description 异步执行器名称
 * @Author chenlinghong
 * @Date 2019/4/24 16:45
 * @Version V1.0
 */
public final class AsyncNameConstant {

    /**
     * session 异步执行器
     */
    public static final String SESSION = "asyncSessionExecutor";

    /**
     * redis 异步执行器
     */
    public static final String REDIS = "asyncRedisExecutor";

    /**
     * 基础业务 异步执行器
     */
    public static final String SERVICE = "asyncServiceExecutor";

    /**
     * 显微镜 异步执行器
     */
    public static final String MICROSCOPE = "asyncMicroscopeExecutor";

    /**
     * 执行器 异步执行器
     */
    public static final String SCHEDULER = "asyncSchedulerExecutor";

    /**
     * 评估器 异步执行器
     */
    public static final String EVALUTOR = "asyncEvalutorExecutor";
}
