package com.yx.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    public static Connection getConnection(DbProfile dbProfile) {
        try {
            Class.forName(dbProfile.getDriver());
            return DriverManager.getConnection(dbProfile.getUrl(), dbProfile.getUsername(), dbProfile.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> List<T> selectList(Connection conn, Mapper mapper) {
        List<T> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql = mapper.getSqlStatement();
            String className = mapper.getResultType();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();

            while (rs.next()) {
                Object obj = constructor.newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, clazz);
                    Method setter = pd.getWriteMethod();
                    setter.invoke(obj, columnValue);
                }

                list.add((T) obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
