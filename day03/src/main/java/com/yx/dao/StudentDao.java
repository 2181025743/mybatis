package com.yx.dao;


import com.yx.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public interface StudentDao {
    default SqlSession getSession() {
        try {
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml")).openSession(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int save(Student student);

    int delete(int id);

    int update(Student student);

    List<Student> selectList();

    Student selectId(int id);
}
