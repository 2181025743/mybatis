package com.yx.mapper;

import com.yx.model.Users;

import java.util.List;

public interface UsersMapper {
    List<Users> selectList();

    Users login(Users user);
}
