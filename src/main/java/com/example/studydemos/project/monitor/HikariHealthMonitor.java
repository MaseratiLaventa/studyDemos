package com.example.studydemos.project.monitor;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Endpoint(id = "hikari")
public class HikariHealthMonitor {
//
//    private final HikariDataSource dataSource;
//
//    public HikariHealthMonitor(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public void start() {
//        Timer timer = new Timer("HikariHealthMonitor", true);
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    HikariPoolMXBean poolStats = dataSource.getHikariPoolMXBean();
//                    System.out.printf(
//                            "[%tF %<tT] HikariCP 状态: active=%d, idle=%d, total=%d, waitingThreads=%d%n",
//                            System.currentTimeMillis(),
//                            poolStats.getActiveConnections(),
//                            poolStats.getIdleConnections(),
//                            poolStats.getTotalConnections(),
//                            poolStats.getThreadsAwaitingConnection()
//                    );
//                } catch (Exception e) {
//                    System.err.println("HikariCP 监控异常: " + e.getMessage());
//                }
//            }
//        }, 0, 5000); // 每5秒打印一次
//    }
//
//    public static void main(String[] args) throws Exception {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl("jdbc:mysql://your-host:3306/yourdb?useSSL=false&serverTimezone=UTC");
//        ds.setUsername("youruser");
//        ds.setPassword("yourpassword");
//        ds.setMaximumPoolSize(100);
//        ds.setMinimumIdle(10);
//
//        new HikariHealthMonitor(ds).start();
//
//        // 模拟系统运行
//        Thread.sleep(Long.MAX_VALUE);
//    }


    private final HikariDataSource dataSource;

    public HikariHealthMonitor(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @ReadOperation
    public Map<String, Object> poolStatus() {
        Map<String, Object> metrics = new HashMap<>();
        HikariPoolMXBean poolBean = dataSource.getHikariPoolMXBean();
        metrics.put("activeConnections", poolBean.getActiveConnections());
        metrics.put("idleConnections", poolBean.getIdleConnections());
        metrics.put("totalConnections", poolBean.getTotalConnections());
        metrics.put("threadsAwaitingConnection", poolBean.getThreadsAwaitingConnection());
        return metrics;
    }
}
