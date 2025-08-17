package com.yx.util;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) {
        try {
            DbProfile dbProfile = XmlParser.parseXml(in);
            return new CustomSqlSessionFactory(dbProfile);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
