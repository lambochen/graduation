package com.chenlinghong.graduation.common.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 配置线程池任务可见, 打印日志
 * @Author chenlinghong
 * @Date 2019/4/7 9:43
 * @Version V1.0
 */
@Configuration
@Slf4j
public class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#execute(runnable)");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#execute(runnable,long)");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#submit(runnable)");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#submit(callable)");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#submitListenable(runnable)");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("VisibleThreadPoolTaskExecutor#submitListenable(Callable)");
        return super.submitListenable(task);
    }

    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if (null == threadPoolExecutor) {
            return;
        }

        log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }
}
