package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class TestUpdate {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(20);
        student.setGender("男");
        boolean update = mapper.update(student);
        System.out.println(update);
    }
}
