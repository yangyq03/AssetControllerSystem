package xyz.alcy.assertcontrollersystem.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.alcy.assertcontrollersystem.utils.JwtUtil;
import xyz.alcy.assertcontrollersystem.utils.ThreadLocalUtil;

import java.util.Map;

//登录拦截器，在未登录时拦截除了登录和注册以外的所有请求
@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //从redis中获取相同的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            //解码jwt令牌
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //获得用户名，并在redis中查找是否有相应的token
            String redisToken = operations.get(claims.get("username"));
            if (redisToken == null) {
                //token已经失效
                throw new RuntimeException();
            }
            if (!redisToken.equals(token)) {
                //token不一致
                throw new RuntimeException();
            }
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //没有抛异常说明token验证成功，放回true，放行
            return true;
        } catch (Exception e) {
            //设置状态码
            response.setStatus(401);
            //拦截
            return false;
        }
    }
}
