package xyz.alcy.assertcontrollersystem.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.alcy.assertcontrollersystem.pojo.Result;
import xyz.alcy.assertcontrollersystem.pojo.User;
import xyz.alcy.assertcontrollersystem.pojo.UserDTO;
import xyz.alcy.assertcontrollersystem.service.AuthService;
import xyz.alcy.assertcontrollersystem.utils.JwtUtil;
import xyz.alcy.assertcontrollersystem.utils.Md5Util;
import xyz.alcy.assertcontrollersystem.utils.ThreadLocalUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注册
    @PostMapping("/register")
    //role   注册人角色  admin -> 管理员    common -> 普通用户
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password,
                           @Pattern(regexp = "^(admin|common)$") String role) {
        //在数据库中查找用户名是否已经存在
        User u = authService.findByUsername(username);
        if (u == null) {
            //没有被占用，需要注册
            authService.register(username, password, role);
            return Result.success();
        } else {
            //已经被占用
            return Result.error("用户名已经被占用");
        }
    }

    //登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = authService.findByUsername(username);
        if (loginUser == null) {
            return Result.error("用户名错误或未注册");
        }
        //已注册
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //密码正确，登录成功
            //生成token令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("role", loginUser.getRole());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            //把token存储在Redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(username, token, 3, TimeUnit.DAYS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //获取用户所有信息
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = authService.findByUsername(username);
        return Result.success(user);
    }

    //更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody UserDTO userDTO) {
        Map<String, Object> map = ThreadLocalUtil.get();
        //通过ThreadLocal获取修改之前的用户名
        String oldUsername = (String) map.get("username");
        //修改之后的用户名
        String newUsername = userDTO.getUsername();
        //判断用户名是否已经被占用，保证用户名的唯一性
        if (newUsername != null &&
                !oldUsername.equals(newUsername) &&     //如果修改了用户名
                authService.findByUsername(newUsername) != null) {
            return Result.error("用户名已被占用");
        }
        //如果修改了用户名，删除redis中对应的token
        if (!oldUsername.equals(newUsername)) {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.getOperations().delete(oldUsername);
        }
        authService.update(userDTO);
        return Result.success();
    }

    //更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        //通过ThreadLocal获取用户名
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //获取原密码
        String password = authService.findByUsername(username).getPassword();
        //判断密码是否正确
        if (!Md5Util.checkPassword(oldPwd, password)) {
            return Result.error("原密码错误");
        }
        //校验两次填写的密码是否正确
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的密码不一致");
        }
        //密码正确，更新密码
        authService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(username);
        return Result.success();
    }

    //修改其他用户的角色
    @PatchMapping("/updateRole")
    public Result updateRole(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String role = params.get("role");
        //参数校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(role)) {
            return Result.error("缺少必要的参数");
        }
        //角色校验
        if (!role.equals("admin") && !role.equals("common")) {
            return Result.error("角色错误");
        }
        //用户不存在
        if (authService.findByUsername(username) == null) {
            return Result.error("用户不存在");
        }
        //不能修改自己的角色
        Map<String, Object> map = ThreadLocalUtil.get();
        if (username.equals(map.get("username"))) {
            return Result.error("不能修改自己的角色");
        }
        //角色没有发生变化
        if (role.equals(authService.findByUsername(username).getRole())) {
            //直接返回成功，减少数据库的访问压力
            return Result.success();
        }
        authService.updateRole(username, role);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(username);
        return Result.success();
    }

    //获取所有用户的信息
    @GetMapping("/allUsers")
    public Result<List<User>> allUsers() {
        List<User> users = authService.findAllUsers();
        return Result.success(users);
    }

}
