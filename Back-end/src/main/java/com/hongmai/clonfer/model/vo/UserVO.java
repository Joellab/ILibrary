package com.hongmai.clonfer.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
@Accessors(chain = true)
public class UserVO {
    /**
     * 登录认证token
     */
    private String accessToken;
    /**
     * 登录续期token //TODO
     */
    private String refreshToken;
    /**
     * 用户数据
     */
    private UserDataVO userData;
}
