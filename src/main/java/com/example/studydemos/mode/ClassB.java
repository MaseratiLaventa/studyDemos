package com.example.studydemos.mode;

import org.springframework.stereotype.Component;

@Component
public class ClassB implements InterfaceA{
    @Override
    public void doSomething() {
        System.out.println("ClassB doSomething");
    }
}
