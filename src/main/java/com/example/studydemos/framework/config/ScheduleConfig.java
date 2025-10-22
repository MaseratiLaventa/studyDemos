package com.example.studydemos.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10); // 设置线程池大小
        taskScheduler.setThreadNamePrefix("scheduled-task-");
        taskScheduler.initialize();
        taskRegistrar.setTaskScheduler(taskScheduler);
    }

    //@Bean
    public TaskExecutor taskExecutor() {
        System.out.println("@Bean执行了");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取当前 JVM 可用的处理器（CPU）数量的方法，返回的是逻辑处理器（线程）的数量，而非物理核心数.
        //例如，在支持超线程技术的 4 核 8 线程 CPU 上，该方法会返回8，而不是4
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("scheduled-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //拒绝策略包含：
        //ThreadPoolExecutor.AbortPolicy() 丢弃任务并抛出RejectedExecutionException异常。
        //ThreadPoolExecutor.DiscardPolicy() 丢弃任务，但是不抛出异常。
        //ThreadPoolExecutor.DiscardOldestPolicy() 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）。
        //ThreadPoolExecutor.CallerRunsPolicy() 由调用线程（提交任务的线程）处理该任务。
        //阻塞队列包含：
        //ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定容量。当队列满且线程数未达最大值时创建新线程，否则触发拒绝策略
        //LinkedBlockingQueue：基于链表的先进先出队列，此队列的长度是 Integer.MAX_VALUE。
        //SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等待一个移除操作，否则插入操作一直处于阻塞状态，
        //PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。

        return executor;
    }

}
