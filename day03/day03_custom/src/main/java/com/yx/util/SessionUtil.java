package com.yx.util;


import java.io.IOException;

public class SessionUtil {
    public static SqlSession session() throws IOException {
        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml")).openSession();
    }

    public static SqlSession session(boolean autoCommit) throws IOException {
        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml")).openSession(autoCommit);
    }
}
