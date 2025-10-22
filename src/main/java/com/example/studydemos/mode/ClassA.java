package com.example.studydemos.mode;

import org.springframework.stereotype.Component;

@Component
public class ClassA implements InterfaceA{
    @Override
    public void doSomething() {
        System.out.println("ClassA doSomething");
    }
}
