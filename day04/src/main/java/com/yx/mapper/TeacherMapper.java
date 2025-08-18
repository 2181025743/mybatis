package com.yx.mapper;

import com.yx.model.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface TeacherMapper {
    
    @Insert("insert into teacher values(null,#{name},#{addr},#{age},#{job},#{sal})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Teacher teacher);
    
    @Delete("delete from teacher where id=#{id}")
    void delete(int id);
    
    @Update("update teacher set name=#{name},addr=#{addr},age=#{age},job=#{job},sal=#{sal} where id=#{id}")
    void update(Teacher teacher);
    
    @Select("select * from teacher order by age")
    List<Teacher> selectList();
    
    @Select("select * from teacher where id=#{id}")
    Teacher selectId(int id);
}