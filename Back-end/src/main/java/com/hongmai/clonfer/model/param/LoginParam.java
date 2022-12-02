package com.hongmai.clonfer.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public class LoginParam {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
