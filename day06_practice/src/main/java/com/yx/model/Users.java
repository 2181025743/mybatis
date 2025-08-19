package com.yx.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Users {
    private int uid;
    private String uname;
    private String pwd;
    private Date lasttime;
    private List<Role> roles;
}