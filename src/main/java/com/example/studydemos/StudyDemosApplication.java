package com.example.studydemos;

import com.example.studydemos.framework.util.SpringContextHolder;
import com.example.studydemos.mode.ClassA;
import com.example.studydemos.project.mcp.McpController;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StopWatch;

@Slf4j
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.example.studydemos"})
public class StudyDemosApplication implements CommandLineRunner, ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StudyDemosApplication.class);
        app.setLogStartupInfo(false);
        System.out.println();
        app.run(args);
       // SpringApplication.run(StudyDemosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===打印ClassA类对象结构===");
        System.out.println(ClassLayout.parseClass(ClassA.class).toPrintable());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StopWatch sw = new StopWatch("使用StopWatch做性能测试");
        sw.start("数据库查询");
        // 模拟操作
        Thread.sleep(100);
        sw.stop();

        sw.start("文件处理");
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());

        System.out.println("=======");

        //使用System.nanoTime()或System.currentTimeMillis()方法，需手动计算时间差
        long startTime = System.nanoTime(); // 开始时间（纳秒）
        long endTime = System.nanoTime(); // 结束时间（纳秒）
        long duration = (endTime - startTime); // 转换为毫秒
        System.out.println("任务时间差: " + duration + " ns");
    }

    static void dumpStackBeforeShutdown() {
//        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
//            log.error("程序即将关闭，当前堆栈:{}", new ThreadDumpEndpoint().textThreadDump());
//        }));
    }
}
