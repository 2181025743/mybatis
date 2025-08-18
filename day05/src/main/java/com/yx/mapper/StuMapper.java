package com.yx.mapper;

import com.yx.model.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StuMapper {
    @Select("select * from student")
    List<Student> selectCondition();

    @Select("<script>" +
            "SELECT * FROM student" +
            " WHERE 1=1" +
            " <if test='name != null and name != \"\"'>" +
            "   AND name LIKE CONCAT('%', #{name}, '%')" +
            " </if>" +
            " <if test='age != null and age != 0'>" +
            "   AND age = #{age}" +
            " </if>" +
            " <if test='gender != null and gender != \"\"'>" +
            "   AND gender = #{gender}" +
            " </if>" +
            "</script>")
    List<Student> selectByCondition(Student stu);
}
