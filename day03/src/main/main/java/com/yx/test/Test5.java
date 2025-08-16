package com.yx.test;

import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test5 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        Student student = new Student()
                .setId(1)
                .setName("张小龙")
                .setAge(25)
                .setGender("男");
        int count = session.update("student.updateStudentById", student);
        System.out.println("影响行数：" + count);

        Student student2 = new Student()
                .setAge(30)
                .setGender("男");

        int rows = session.update("student.updateAgeByGender", student2);
        session.close();
        System.out.println("共更新：" + rows + "行");
    }
}
