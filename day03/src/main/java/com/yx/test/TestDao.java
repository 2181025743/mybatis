package com.yx.test;

import com.yx.dao.StudentDao;
import com.yx.dao.impl.StudentDaoImpl;
import com.yx.model.Student;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        StudentDao dao = new StudentDaoImpl();
        list(dao);
        save(dao);
        update(dao);
        delete(dao);

    }

    public static void list(StudentDao dao) {
        List<Student> list = dao.selectList();
        list.forEach(System.out::println);
    }

    public static void save(StudentDao dao) {
        Student student = new Student(1, "杜甫", 20, "男");
        System.out.println(dao.save(student) > 0 ? "保存成功" : "保存失败");
    }

    public static void update(StudentDao dao) {
        Student student = dao.selectId(5);
        if (student != null) {
            student.setName("陆游").setAge(25);
            System.out.println((dao.update(student) > 0 ? "更新成功" : "更新失败"));
        } else {
            System.out.println("未找到ID为5的学生，无法更新");
        }

    }

    public static void delete(StudentDao dao) {
        System.out.println((dao.delete(5) > 0 ? "删除成功" : "删除失败"));
    }
}
