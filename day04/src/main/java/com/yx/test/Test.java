package com.yx.test;

import com.yx.mapper.TeacherMapper;
import com.yx.model.Teacher;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        Teacher teacher = new Teacher()
                .setName("张老师")
                .setAddr("北京市")
                .setAge(35)
                .setJob("数学老师")
                .setSal(8000);
        teacherMapper.save(teacher);
        System.out.println("插入成功，ID为: " + teacher.getId());

        Teacher t = teacherMapper.selectId(teacher.getId());
        System.out.println("查询单个教师: " + t.getName());
        t.setSal(8500);
        teacherMapper.update(t);
        System.out.println("更新后的工资: " + teacherMapper.selectId(t.getId()).getSal());
        List<Teacher> teachers = teacherMapper.selectList();
        System.out.println("教师总数: " + teachers.size());
        teacherMapper.delete(teacher.getId());
        System.out.println("删除成功");

        session.close();
    }
}
