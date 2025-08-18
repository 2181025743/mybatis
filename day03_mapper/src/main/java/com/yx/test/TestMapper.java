package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestMapper {
    public static void main(String[] args) {
        SqlSession session = null;
        try {
            session = SessionUtil.session(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        mapper.insert(new Student(0, "李贺", 22, "男"));
        mapper.update(new Student(0, "李贺", 25, "男"));
        System.out.println(mapper.selectId(20));
        mapper.delete(26);
        List<Student> list = mapper.selectList();
        list.forEach(System.out::println);
        session.close();

    }
}
