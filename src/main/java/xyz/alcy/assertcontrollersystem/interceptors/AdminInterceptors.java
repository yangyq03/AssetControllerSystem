package xyz.alcy.assertcontrollersystem.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.alcy.assertcontrollersystem.utils.ThreadLocalUtil;

import java.util.Map;

//管理员身份拦截器，拦截一些只有管理员才能访问的接口
@Component
public class AdminInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //通过token取得业务数据并获取用户角色
        Map<String, Object> map = ThreadLocalUtil.get();
        String role = (String) map.get("role");
        if ("admin".equals(role)) {
            return true;
        } else {
            //如果不是管理员，拦截
            response.setStatus(403);
            return false;
        }
    }
}
