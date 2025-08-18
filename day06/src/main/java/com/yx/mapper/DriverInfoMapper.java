package com.yx.mapper;

import com.yx.model.DriverInfo;
import com.yx.model.DriverQuery;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface DriverInfoMapper {
    @Insert("insert into driverinfo values(null,#{dtype},#{pid})")
    void insert(DriverInfo info);

    List<DriverQuery> selectList();
}
