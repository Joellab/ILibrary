package com.hongmai.clonfer.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
@Data
public class AuthEmailParam {

    @NotBlank(message = "真实姓名不能为空")
    private String fullname;

    @NotBlank(message = "邮箱不能为空")
    private String email;
}
