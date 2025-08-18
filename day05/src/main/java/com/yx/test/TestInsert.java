package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestInsert {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> stus = new ArrayList<>();
        stus.add(new Student(null, "李白", 20, "男"));
        stus.add(new Student(null, "杜甫", 21, "男"));
        stus.add(new Student(null, "白居易", 22, "女"));

        int i = mapper.insertBench(stus);
        System.out.println(i);
    }
}
