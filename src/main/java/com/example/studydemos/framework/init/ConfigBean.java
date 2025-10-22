package com.example.studydemos.framework.init;

import com.example.studydemos.framework.algo.AlgoDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigBean {
    @Bean
    public AlgoDemo ruleService() {
        System.out.println("@Bean注解执行了");
        return new AlgoDemo();
    }
}
