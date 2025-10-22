package com.example.studydemos.framework.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanProcessorInit implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("globalInit")) {
            System.out.println("BeanPostProcessor-postProcessBeforeInitialization执行了");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("globalInit")) {
            System.out.println("BeanPostProcessor-postProcessAfterInitialization执行了");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
