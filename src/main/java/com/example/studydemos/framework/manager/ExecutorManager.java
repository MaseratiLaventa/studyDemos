package com.example.studydemos.framework.manager;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ExecutorManager {
    private ExecutorManager() {
    }

    public static final ScheduledThreadPoolExecutor SCHEDULED_POOLS = new ScheduledThreadPoolExecutor(5);

    private static class SingletonInstance {
        private static final ExecutorService fixedThreadPool = new ThreadPoolExecutor(30, 60, 600, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20000));

    }

    public static ExecutorService getFixedThreadPool() {
        return SingletonInstance.fixedThreadPool;
    }

}
