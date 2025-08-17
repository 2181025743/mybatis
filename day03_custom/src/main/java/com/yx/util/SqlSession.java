package com.yx.util;

public interface SqlSession {
    <T> T getMapper(Class<T> mapperClass);

    void close();
}
