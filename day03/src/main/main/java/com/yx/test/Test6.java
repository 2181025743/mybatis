package com.yx.test;

import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test6 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        List<Student> studentList = session.selectList("student.selectAllStudents");
        for (Student s : studentList) {
            System.out.println(s);
        }
        Student student1 = session.selectOne("student.selectStudentById", 1);
        System.out.println(student1);
        Student queryStudent = new Student()
                .setGender("男")
                .setAge(20);
        List<Student> resultList = session.selectList("student.selectByCondition", queryStudent);
        session.close();
        for (Student s : resultList) {
            System.out.println(s);
        }
    }
}
