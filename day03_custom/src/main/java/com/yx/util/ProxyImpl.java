package com.yx.util;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;
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

        // 先尝试从XML配置中查找
        Mapper mapper = map.get(key);

        // 如果XML中没有，检查是否有@Select注解
        if (mapper == null) {
            Select selectAnnotation = method.getAnnotation(Select.class);
            if (selectAnnotation != null) {
                mapper = new Mapper();
                mapper.setSqlStatement(selectAnnotation.value());
                // 获取返回类型
                Class<?> returnType = method.getReturnType();
                if (returnType.isAssignableFrom(List.class)) {
                    // 如果是List，需要获取泛型类型
                    Type genericReturnType = method.getGenericReturnType();
                    if (genericReturnType instanceof ParameterizedType) {
                        ParameterizedType paramType = (ParameterizedType) genericReturnType;
                        Type[] actualTypeArguments = paramType.getActualTypeArguments();
                        if (actualTypeArguments.length > 0) {
                            mapper.setResultType(actualTypeArguments[0].getTypeName());
                        }
                    }
                } else {
                    mapper.setResultType(returnType.getName());
                }
            }
        }

        if (mapper == null) {
            throw new RuntimeException("Mapper 中未注册该方法！");
        }

        return DBUtil.selectList(connection, mapper);
    }
}