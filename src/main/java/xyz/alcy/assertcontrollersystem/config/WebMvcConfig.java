package xyz.alcy.assertcontrollersystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//允许跨域请求
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") //设置允许跨域的路径
                .allowedOriginPatterns("*") //允许所有来源访问
                .allowedMethods("*") //允许所有请求方法
                .allowedHeaders("*") //允许所有请求头
                .allowCredentials(true); //允许发送 Cookie
    }
}