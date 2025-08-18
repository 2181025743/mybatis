package com.yx.mapper;

import com.yx.model.Student;

import java.util.List;

public interface StudentMapper {
    void insert(Student student);

    void update(Student student);

    void delete(int id);

    List<Student> selectList();

    Student selectId(int id);
    
    List<Student> selectByCondition(Student student);
}
