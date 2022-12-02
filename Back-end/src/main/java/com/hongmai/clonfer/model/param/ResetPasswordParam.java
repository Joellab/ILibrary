package com.hongmai.clonfer.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
@Data
public class ResetPasswordParam {
    @NotBlank(message = "标识符不能为空")
    private String code;

    @NotBlank(message = "密码不能为空")
    private String password;
}
