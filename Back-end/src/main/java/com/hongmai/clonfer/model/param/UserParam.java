package com.hongmai.clonfer.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public class UserParam {
    @NotNull(message = "用户ID不能为空", groups = Update.class)
    private Long id;

    @NotBlank(message = "用户名不能为空", groups = CreateUser.class)
    @Length(min = 4, max = 12, message = "用户名长度为4-12位", groups = CreateUser.class)
    private String username;

    @NotBlank(message = "密码不能为空", groups = CreateUser.class)
    @Length(min = 4, max = 12, message = "密码长度为4-15位", groups = CreateUser.class)
    private String password;

    private List<Long> roleIds;

    private List<Long> companyIds;

    public interface Update {}

    public interface CreateUser{}
}
