package com.yx.test;

import com.yx.model.Teacher;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test3 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        Teacher teacher = new Teacher();
        teacher.setName("张三");
        teacher.setAddr("上海");
        int insert = session.insert("insertTeacher", teacher);
        System.out.println(insert);
        System.out.println("插入后主键ID: " + teacher.getId());
    }
}
