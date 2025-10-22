package com.example.studydemos.framework.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class GlobalInit2 implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean接口执行了");
    }
}
