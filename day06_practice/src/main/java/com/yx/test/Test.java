package com.yx.test;

import com.yx.mapper.UsersMapper;
import com.yx.model.Permission;
import com.yx.model.Role;
import com.yx.model.Users;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        UsersMapper mapper = session.getMapper(UsersMapper.class);
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String uname = sc.next();
        System.out.print("请输入密码：");
        String pwd = sc.next();

        Users users = new Users();
        users.setUname(uname);
        users.setPwd(pwd);

        Users loginUser = mapper.login(users);
        if (loginUser == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("欢迎用户：" + loginUser.getUname());
            for (Role role : loginUser.getRoles()) {
                System.out.println("角色：" + role.getRname());
                System.out.println("可操作菜单：");
                for (Permission perm : role.getPerms()) {
                    System.out.println("   <a href='" + perm.getUrl() + "'>" + perm.getPname() + "</a>");
                }
            }

            // 更新最后访问时间
            mapper.updateLastLogin(loginUser);
        }

        session.close();
    }
}