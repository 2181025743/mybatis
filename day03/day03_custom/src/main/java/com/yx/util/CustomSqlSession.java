package com.yx.util;

public class CustomSqlSession implements SqlSession {
    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return null;
    }

    @Override
    public void close() {

    }
}
