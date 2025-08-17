package com.yx.mapper;


import com.yx.model.Student;
import com.yx.util.Select;

import java.util.List;

public interface StudentDao {
    @Select("select * from student")
    List<Student> selectList();
}
