package com.yx.Test;


import com.yx.mapper.DriverInfoMapper;
import com.yx.mapper.PersonMapper;
import com.yx.model.DriverQuery;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {

        SqlSession session = SessionUtil.session(true);
        PersonMapper p_mapper = session.getMapper(PersonMapper.class);
        // p_mapper.insert(new Person(0, "李白", 22));
        // p_mapper.insert(new Person(0, "李贺", 21));
        // p_mapper.insert(new Person(0, "杜甫", 24));
        DriverInfoMapper d_mapper = session.getMapper(DriverInfoMapper.class);
        // d_mapper.insert(new DriverInfo(0, "A", 1));
        // d_mapper.insert(new DriverInfo(0, "B", 2));
        // d_mapper.insert(new DriverInfo(0, "C", 3));
        // session.close();
        List<DriverQuery> driverQueries = d_mapper.selectList();
        driverQueries.forEach(System.out::println);
    }
}
