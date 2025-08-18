package com.yx.mapper;

import com.yx.model.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectByCondition(Student student);

    List<Student> selectByConditionTrim(Student student);

    boolean update(Student student);

    int deleteList(List<Integer> ids);

    int insertBench(List<Student> list);

    List<Student> selectByName(String name);
}