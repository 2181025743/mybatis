package com.yx.test;

import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test4 {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        int rows = session.delete("student.deleteById", 14);
        System.out.println("删除数量：" + rows);
        String name = "李贺";
        int rows2 = session.delete("student.deleteByName", name);
        System.out.println("删除条数：" + rows2);
        String nameLike = "李%";
        int rows3 = session.delete("student.deleteByLike3", nameLike);
        System.out.println("删除条数：" + rows3);
        session.close();
    }
}
