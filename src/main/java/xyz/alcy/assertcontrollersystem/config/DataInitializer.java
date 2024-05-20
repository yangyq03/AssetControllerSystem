package xyz.alcy.assertcontrollersystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xyz.alcy.assertcontrollersystem.mapper.AuthMapper;
import xyz.alcy.assertcontrollersystem.utils.Md5Util;

//在程序运行的时候，如果用户表为空，初始化一个管理员账号
@Component
public class DataInitializer {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private final AuthMapper authMapper;

    public DataInitializer(AuthMapper userMapper) {
        this.authMapper = userMapper;
    }

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            // 检查是否有用户存在
            if (authMapper.findAllUsers().isEmpty()) {
                String md5Pwd = Md5Util.getMD5String(adminPassword);
                authMapper.add(adminUsername, md5Pwd, "admin");
            }
        };
    }
}
