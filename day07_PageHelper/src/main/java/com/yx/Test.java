package com.yx;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.mapper.StuMapper;
import com.yx.model.Student;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session();
        StuMapper mapper = session.getMapper(StuMapper.class);

        PageHelper.startPage(1, 3);

        List<Student> students = mapper.selectCondition();
        PageInfo<Student> pageInfo = new PageInfo<>(students);

        // 打印结果
        System.out.println("当前页: " + pageInfo.getPageNum());
        System.out.println("每页条数: " + pageInfo.getPageSize());
        System.out.println("总页数: " + pageInfo.getPages());
        System.out.println("总记录数: " + pageInfo.getTotal());
        pageInfo.getList().forEach(System.out::println);

        session.close();
    }
}
