package com.example.studydemos.framework.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GlobalInit {

    static {
        System.out.println("静态代码块执行了");
    }

}
