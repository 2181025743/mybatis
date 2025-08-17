package com.yx.util;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

@AllArgsConstructor
public class ProxyImpl implements InvocationHandler {
    private Map<String, Mapper> map;
    private Connection connection;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + "." + methodName;
        Mapper mapper = map.get(key);
        if (mapper == null) {
            throw new RuntimeException("Mapper 中未注册该方法！");
        }
        return new DBUtil().selectList(connection, mapper);
    }
}
