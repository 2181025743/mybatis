package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student condition = new Student();
        condition.setName("杜");
        List<Student> result1 = mapper.selectByCondition(condition);
        result1.forEach(System.out::println);
        System.out.println("1111111");
        condition.setGender("男");
        List<Student> result2 = mapper.selectByCondition(condition);
        result2.forEach(System.out::println);
        System.out.println("222222");
        condition.setAge(21);
        List<Student> result3 = mapper.selectByCondition(condition);
        result3.forEach(System.out::println);
        System.out.println("333");
    }
}
