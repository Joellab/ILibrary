package com.hongmai.clonfer.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public class RegisterParam {

    @NotBlank(message = "用户名不能为空", groups = CreateUser.class)
    @Length(min = 4, max = 12, message = "用户名长度为4-12位", groups = CreateUser.class)
    private String username;

    @NotBlank(message = "密码不能为空", groups = CreateUser.class)
    @Length(min = 4, max = 15, message = "密码长度为4-15位", groups = CreateUser.class)
    private String password;

    @NotBlank(message = "邮箱不能为空", groups = CreateUser.class)
    private String email;

    @NotBlank(message = "真实姓名不能为空", groups = CreateUser.class)
    private String fullname;

    @NotBlank(message = "组织归属不能为空", groups = CreateUser.class)
    private String organization;

    @NotBlank(message = "验证码", groups = CreateUser.class)
    private String code;

    public interface CreateUser{}
}
