package com.example.studydemos.project.job;


import com.example.studydemos.framework.manager.ExecutorManager;
import com.example.studydemos.framework.util.SpringContextHolder;
import com.example.studydemos.framework.util.UuidUtil;
import com.example.studydemos.mode.InterfaceA;
import com.example.studydemos.project.basic.School;
import com.example.studydemos.project.basic.Student;
import com.example.studydemos.project.basic.StudentService;
import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class TestJob {
    static List<Object> keeper = new ArrayList<>();
    List objects = Lists.newArrayList();

    //@Scheduled(initialDelay = 500, fixedDelay = 500)
    public void testJob() {
        while (true) {
            Object obj = new Object[1024 * 1024 * 20];
        }
    }

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

   // @Scheduled(initialDelay = 3000, fixedDelay = 1000)
    public void testJob2() {
        log.info("定时2任务执行了，线程：{}", Thread.currentThread().getName());
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }

    @Autowired
    List<InterfaceA> interfaceAS;


    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void testJob3() throws InterruptedException {
//        test();
//        log.info("定时3任务执行了，线程：{}", Thread.currentThread().getName());
    }


    private static void test() throws InterruptedException {
        Thread.sleep(4000);
    }


    /**
     * 每隔3秒打印一次连接池使用情况
     */
    //@Scheduled(initialDelay = 300, fixedDelay = 300)
    public void monitorHikariPool() {
        HikariDataSource dataSource = SpringContextHolder.getBean(HikariDataSource.class);
        HikariPoolMXBean poolStats = dataSource.getHikariPoolMXBean();
        if (poolStats == null) return;
        log.info("HikariCP 状态: active={}, idle={}, total={}, waitingThreads={}",
                poolStats.getActiveConnections(),
                poolStats.getIdleConnections(),
                poolStats.getTotalConnections(),
                poolStats.getThreadsAwaitingConnection());
    }

    @Autowired
    StudentService studentService;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    public TransactionDefinition transactionDefinition;

    // @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void useConnectJob() {
        for (int i = 0; i < 100; i++) {
            ExecutorManager.getFixedThreadPool().execute(() -> {
                log.info("线程：{},执行sql操作", Thread.currentThread().getName());
                // 手动设置事务
                TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
                Student student = studentService.getById(new Random().nextInt(3000));
                if (student != null) {
                    student.setState(1);
                    studentService.updateById(student);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                dataSourceTransactionManager.commit(transactionStatus);
            });

        }
    }
//    @Scheduled(initialDelay = 100, fixedDelay = 100)
//    public void useConnectJob2() {
//        for (int i = 0; i < 100; i++) {
//            ExecutorManager.getFixedThreadPool().execute(() -> {
//                Student student = studentService.getById(new Random().nextInt(3000));
//                // log.info("查询结果：{}", student);
//            });
//
//        }
//    }
}
