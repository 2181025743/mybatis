package com.yx.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
RetentionPolicy.SOURCE:只保留在源文件，当编译成class文件后，注解被遗弃(.java)
RetentionPolicy.CLASS: 注解被保留到class文件中，jvm加载class文件时被遗弃,默认的生命周期(.class)
RetentionPolicy.RUNTIME:不仅被保留在class文件中，jvm加载class文件，仍然存在(内存中的字节码)
 */
@Retention(RetentionPolicy.RUNTIME)
/*
@Target 表明注解修饰的对象范围
        1.METHOD 用于描述方法
        2.FIELD 用于描述域
        3.CONSTRUCTOR 用于描述构造器
        4.LOCAL_VARIABLE 用于描述局部变量
        5.PACKAGE 用于描述包
        6.PARAMETER 用于描述参数
        7.TYPE 用于描述类、接口、enum声明
 */
@Target(ElementType.METHOD)
public @interface Select {
    String value();
}
