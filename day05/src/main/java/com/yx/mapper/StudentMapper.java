package com.yx.mapper;

import com.yx.model.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectByCondition(Student student);
}