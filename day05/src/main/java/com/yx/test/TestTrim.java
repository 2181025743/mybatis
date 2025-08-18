package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestTrim {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        Student condition = new Student();

        condition.setName("杜");
        List<Student> result1 = mapper.selectByConditionTrim(condition);
        result1.forEach(System.out::println);
    }
}
