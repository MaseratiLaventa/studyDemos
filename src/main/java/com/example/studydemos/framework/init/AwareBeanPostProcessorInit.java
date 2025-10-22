package com.example.studydemos.framework.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AwareBeanPostProcessorInit implements InstantiationAwareBeanPostProcessor {
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("globalInit1")) {
            System.out.println("InstantiationAwareBeanPostProcessor-postProcessAfterInstantiation执行了");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("globalInit1")) {
            System.out.println("InstantiationAwareBeanPostProcessor-postProcessProperties执行了");
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("globalInit1")) {
            System.out.println("InstantiationAwareBeanPostProcessor-postProcessBeforeInstantiation执行了");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }
}
