package com.yx.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomSqlSession implements SqlSession {
    private DbProfile dbProfile;

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return null;
    }

    @Override
    public void close() {

    }
}
