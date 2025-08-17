package com.yx.test;


import com.yx.mapper.StudentDao;
import com.yx.model.Student;
import com.yx.util.Resources;
import com.yx.util.SqlSession;
import com.yx.util.SqlSessionFactory;
import com.yx.util.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class TestAnnotation {
    public static void main(String[] args) {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession session = factory.openSession();
        StudentDao mapper = session.getMapper(StudentDao.class);
        List<Student> list = mapper.selectList();
        list.forEach(System.out::println);
        session.close();
    }
}
