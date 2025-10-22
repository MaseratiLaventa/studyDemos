package com.example.studydemos.framework.util;

import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
public class RuntimeUtil {

    private static String runtimeName = "";
    private static Long pid = 0L;

    /**
     * 获取运行时名称
     *
     * @return
     */
    public static String getRuntimeName() {
        if (!StringUtils.hasText(runtimeName)) {
            synchronized (runtimeName) {
                if (!StringUtils.hasText(runtimeName)) {
                    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
                    runtimeName = runtimeMXBean.getName();
                }
            }
        }
        return runtimeName;
    }

    /**
     * 获取运行时ID
     *
     * @return
     */
    public static Long getPid() {
        if (pid == null || pid < 1L) {
            synchronized (runtimeName) {
                if (pid == null || pid < 1L) {
                    pid = Long.valueOf(getRuntimeName().split("@")[0]);
                }
            }
        }
        return pid;
    }

    /**
     * 获取线程ID
     *
     * @return
     */
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }
}
