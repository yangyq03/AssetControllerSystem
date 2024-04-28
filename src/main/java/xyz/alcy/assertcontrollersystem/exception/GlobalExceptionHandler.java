package xyz.alcy.assertcontrollersystem.exception;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.alcy.assertcontrollersystem.pojo.Result;

@RestControllerAdvice   //全局异常处理器   用于处理异常并将失败原因响应回请求方
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
