package com.hongmai.clonfer.controller.api.auth;

import com.hongmai.clonfer.model.param.*;
import com.hongmai.clonfer.model.vo.UserVO;
import com.hongmai.clonfer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Slf4j
@RestController
@CrossOrigin
@Api(tags="系统鉴权")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public UserVO login(@RequestBody @Validated LoginParam user) {
        return userService.login(user);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public void register(@RequestBody @Validated RegisterParam user) {
        userService.register(user);
    }

    @ApiOperation("发送验证邮件")
    @PostMapping("/register/sendAuthEmail")
    public void sendAuthEmail(@RequestBody @Validated AuthEmailParam param) {
        userService.sendAuthEmail(param);
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public void resetPassword(@RequestBody @Validated ResetPasswordParam param) {
        userService.resetPassword(param);
    }

    @ApiOperation("发送重置密码邮件")
    @PostMapping("/resetPassword/sendResetPasswordEmail")
    public void sendResetPasswordEmail(@RequestBody @Validated ResetPasswordRequestParam param) {
        userService.sendResetPasswordEmail(param);

    }
}