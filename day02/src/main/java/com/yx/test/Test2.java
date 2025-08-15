package com.yx.test;

import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        Student student = new Student().setName("王某人").setAge(20).setGender("男");
        int insert = session.insert("insertObject", student);
        System.out.println(insert);
        session.close();

    }
}
