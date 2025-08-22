package com.yx.test;

import com.yx.mapper.StuMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestSql1 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StuMapper mapper = session.getMapper(StuMapper.class);

        Student stu = new Student()
                .setName("李")
                .setAge(20)
                .setGender("男");
        List<Student> list = mapper.selectByCondition(stu);
        list.forEach(System.out::println);

        session.close();
    }
}
