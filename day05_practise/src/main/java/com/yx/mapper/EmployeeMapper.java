package com.yx.mapper;

import com.yx.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {
    int insertBatch(List<Employee> Employees);

    List<Employee> selectByCity(String city);

    BigDecimal avgSalaryByProvince(String province);

    int updateByCityBatch(@Param("city") String city,
                          @Param("sal") BigDecimal sal,
                          @Param("job") String job);

    int deleteByIds(Integer[] ids);
}
