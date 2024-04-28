package xyz.alcy.assertcontrollersystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class User {
    @NonNull    //不能为null
    private Integer id;
    private String username;
    @JsonIgnore //在转成json时忽略password
    private String password;
    @NotEmpty
    @Email
    private String email;
    private String role;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
