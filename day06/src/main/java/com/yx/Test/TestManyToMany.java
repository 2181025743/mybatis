package com.yx.Test;


import com.yx.mapper.UsersMapper;
import com.yx.model.Role;
import com.yx.model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TestManyToMany {
    public static void main(String[] args) throws IOException {
        SqlSession session = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("SqlMapConfig.xml"))
                .openSession();

        UsersMapper mapper = session.getMapper(UsersMapper.class);
        List<Users> list = mapper.selectList();

        for (Users user : list) {
            System.out.println(user.getUname() + "\t" + user.getPwd());
            System.out.println("具有的角色：");
            for (Role role : user.getRoles()) {
                System.out.println("\t" + role.getRname());
            }
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String uname = scanner.next();
        System.out.println("请输入密码:");
        String pwd = scanner.next();

        Users u = new Users();
        u.setUname(uname);
        u.setPwd(pwd);

        Users user = mapper.login(u);
        if (user == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("欢迎用户：" + user.getUname());
            System.out.println("您的角色：");
            for (Role role : user.getRoles()) {
                System.out.println("\t" + role.getRname());
            }
        }
    }

}
