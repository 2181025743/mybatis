package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestByName {
    public static void main(String[] args) throws IOException {

        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        List<Student> students = mapper.selectByName("李");
        students.forEach(System.out::println);
    }
}
