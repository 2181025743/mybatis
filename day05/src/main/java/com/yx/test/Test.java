package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);

// 举例1：只查询 name 字段
        Student condition = new Student();
        condition.setName("杜");
        List<Student> result1 = mapper.selectByCondition(condition);

// 举例2：查询 name 和 gender
        condition.setGender("男");
        List<Student> result2 = mapper.selectByCondition(condition);

// 举例3：全部字段一起查
        condition.setAge(21);
        List<Student> result3 = mapper.selectByCondition(condition);
    }
}
