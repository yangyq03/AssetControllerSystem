package xyz.alcy.assertcontrollersystem.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.alcy.assertcontrollersystem.pojo.User;

import java.util.List;

@Mapper
public interface AuthMapper {
    //添加用户
    @Insert("insert into users(username, password, role, create_time, update_time) " +
            "values(#{username}, #{password}, #{role}, now(), now())")
    void add(String username, String password, String role);

    //根据用户名查找用户
    @Select("select * from users where username=#{username}")
    User findByUsername(String username);

    //更新用户
    @Update("update users set username=#{username}, email=#{email}, update_time=now() where id=#{id}")
    void update(User user);

    //更新用户密码
    @Update("update users set password=#{newPwd},update_time=now() where id=#{userId}")
    void updatePwd(String newPwd, Integer userId);

    //修改用户角色
    @Update("update users set role=#{role},update_time=now() where username=#{username}")
    void updateRole(String username, String role);

    //获取所有用户的信息
    @Select("select * from users")
    List<User> findAllUsers();
}
