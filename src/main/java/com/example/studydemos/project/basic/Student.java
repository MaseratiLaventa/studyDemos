package com.example.studydemos.project.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class Student {
    private Integer id;
    private String studentId;
    private String name;
    private String idCard;
    private String className;
    private String phone;
    private String webchat;

    private String professionId;
    private Integer collegeId;
    private Integer schoolId;
    private String pwd;
    private Integer userId;
    private Integer state;
    private String createDate;
    private String updateDate;
    private Object[] obj = new Object[1024 * 1024 * 20];


}
