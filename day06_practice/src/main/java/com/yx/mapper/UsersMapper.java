package com.yx.mapper;

import com.yx.model.Users;

public interface UsersMapper {
    Users login(Users user);

    void updateLastLogin(Users user);
}
