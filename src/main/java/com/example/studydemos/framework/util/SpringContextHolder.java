package com.example.studydemos.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;  // 注入ApplicationContext
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);  // 按类型获取Bean
    }

    public static Object getBean(String name) {
        return context.getBean(name);  // 按名称获取Bean
    }
}
