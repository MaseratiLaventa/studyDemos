package com.example.studydemos.project.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

@Mapper
@Resource
public interface StudentMapper extends BaseMapper<Student> {
}
