package com.example.studydemos.framework.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GlobalInit4 {

    @PostConstruct
    public void testByPostConstruct() {
        System.out.println("@PostConstruct注解执行了");
    }
}
