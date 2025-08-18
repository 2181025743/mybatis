package com.yx.mapper;


import com.yx.model.Person;
import org.apache.ibatis.annotations.Insert;

public interface PersonMapper {
    @Insert("insert into person values(null,#{pname},#{page})")
    void insert(Person person);
}