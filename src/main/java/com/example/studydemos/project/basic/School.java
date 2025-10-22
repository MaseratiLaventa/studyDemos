package com.example.studydemos.project.basic;

import lombok.Data;

@Data
public class School {

    private Integer id;
    private String name;
    private String address;
    private int[] tel = new int[2];
    private Object [] email = new Object[2000];
}
