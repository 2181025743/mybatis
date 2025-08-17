package com.yx;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml")).openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectList();
        list.forEach(System.out::println);
        // mapper.delete(1);
        // session.commit();
        session.close();
    }
}
