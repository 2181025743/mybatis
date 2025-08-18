package com.yx.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionUtil {
    public static SqlSession session() throws IOException {
        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml")).openSession();
    }

    public static SqlSession session(boolean autoCommit) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        return new SqlSessionFactoryBuilder().build(inputStream).openSession(autoCommit);
    }
}
