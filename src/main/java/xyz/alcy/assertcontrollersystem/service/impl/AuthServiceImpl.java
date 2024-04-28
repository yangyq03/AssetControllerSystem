package xyz.alcy.assertcontrollersystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.alcy.assertcontrollersystem.mapper.AuthMapper;
import xyz.alcy.assertcontrollersystem.pojo.User;
import xyz.alcy.assertcontrollersystem.pojo.UserDTO;
import xyz.alcy.assertcontrollersystem.service.AuthService;
import xyz.alcy.assertcontrollersystem.utils.Md5Util;
import xyz.alcy.assertcontrollersystem.utils.ThreadLocalUtil;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public void register(String username, String password, String role) {
        //使用md5对密码进行加密
        String md5String = Md5Util.getMD5String(password);

        //添加用户
        authMapper.add(username, md5String, role);
    }

    @Override
    public User findByUsername(String username) {
        return authMapper.findByUsername(username);
    }

    @Override
    public void update(UserDTO userDTO) {
        authMapper.update(userDTO);
    }

    @Override
    public void updatePwd(String newPwd) {
        //通过ThreadLocal获取创建者id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        authMapper.updatePwd(Md5Util.getMD5String(newPwd), userId);
    }

    @Override
    public void updateRole(String username, String role) {
        authMapper.updateRole(username, role);
    }

    @Override
    public List<User> findAllUsers() {
        return authMapper.findAllUsers();
    }
}
