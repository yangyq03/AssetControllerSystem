package xyz.alcy.assertcontrollersystem.service;


import xyz.alcy.assertcontrollersystem.pojo.User;
import xyz.alcy.assertcontrollersystem.pojo.UserDTO;

import java.util.List;

public interface AuthService {
    //注册
    void register(String username, String password);

    //根据用户名查找用户
    User findByUsername(String username);

    //更新用户信息
    void update(UserDTO userDTO);

    //修改用户密码
    void updatePwd(String newPwd);

    //修改其他用户的角色
    void updateRole(String username, String role);

    //获取所有用户的信息
    List<User> findAllUsers();
}
