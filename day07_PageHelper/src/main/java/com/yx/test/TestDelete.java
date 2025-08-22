package com.yx.test;

import com.yx.mapper.StudentMapper;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Arrays;

public class TestDelete {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        int i = mapper.deleteList(Arrays.asList(24, 25));
        System.out.println(i);
    }
}
