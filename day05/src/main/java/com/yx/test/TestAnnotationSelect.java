package com.yx.test;

import com.yx.mapper.StuMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class TestAnnotationSelect {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StuMapper mapper = session.getMapper(StuMapper.class);

        List<Student> students = mapper.selectCondition();
        students.forEach(System.out::println);
        session.close();
    }
}
