package com.yx.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;


public class CustomSqlSession implements SqlSession {
    private DbProfile profile;
    private Connection connection;

    public CustomSqlSession(DbProfile profile) {
        this.profile = profile;
        connection = DBUtil.getConnection(profile);
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(),
                new Class[]{mapperClass}, new ProxyImpl(profile.getMappers(), connection));
    }

    @Override
    public void close() throws RuntimeException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        }
    }
}