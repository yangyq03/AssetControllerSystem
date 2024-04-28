package xyz.alcy.assertcontrollersystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.alcy.assertcontrollersystem.interceptors.LoginInterceptors;
import xyz.alcy.assertcontrollersystem.interceptors.AdminInterceptors;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptors loginInterceptors;
    @Autowired
    AdminInterceptors adminInterceptors;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录相关
        registry.addInterceptor(loginInterceptors).excludePathPatterns("/api/auth/login", "/api/auth/register");
        //管理员相关
        registry.addInterceptor(adminInterceptors).addPathPatterns("/api/user/updateRole", "/api/asset/manage/**");
    }
}
