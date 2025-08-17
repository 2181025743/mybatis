package com.yx.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomSqlSessionFactory implements SqlSessionFactory {
    private DbProfile dbProfile;

    @Override
    public SqlSession openSession() {
        return new CustomSqlSession(dbProfile);
    }
}
