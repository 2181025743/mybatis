package com.yx.util;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) {
        return new CustomSqlSessionFactory(); // 目前先返回空实现
    }
}
