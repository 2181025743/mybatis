package yx.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import yx.entity.Student;
import yx.entity.StudentExample;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student row);

    int insertSelective(Student row);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Student row, @Param("example") StudentExample example);

    int updateByExample(@Param("row") Student row, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student row);

    int updateByPrimaryKey(Student row);
}