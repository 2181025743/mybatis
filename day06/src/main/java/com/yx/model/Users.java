package com.yx.model;

import lombok.Data;

import java.util.List;

@Data
public class Users {
    private int uid;
    private String uname;
    private String pwd;
    private List<Role> roles;
}