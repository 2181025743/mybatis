package com.yx.util;

public class CustomSqlSessionFactory implements SqlSessionFactory {
    @Override
    public SqlSession openSession() {
        return new CustomSqlSession();
    }
}
