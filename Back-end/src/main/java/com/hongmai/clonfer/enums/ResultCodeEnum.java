package com.hongmai.clonfer.enums;

import lombok.Getter;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(0000, "OK"),

    UNAUTHORIZED(1001, "用户未登录"),

    FORBIDDEN(1002, "用户权限受限"),

    LOGIN_FAILED(1003, "用户名或密码错误"),

    USER_EXIST(1004, "用户名已存在"),

    EMAIL_EXIST(1005, "邮箱已存在"),

    CODE_INVALID(1006, "校验码错误"),

    SEND_AUTH_EMAIL_FAILED(1007, "验证邮件发送失败"),

    USER_NOT_EXIST(1008, "用户不存在"),

    ORGANIZATION_NOT_EXIST(1009, "组织不存在"),

    USER_INVALID(1010, "账户已被禁用"),

    VALIDATE_FAILED(2001, "参数校验失败"),

    FAILED(2002, "请求被驳回"),

    DATABASE_EXCEPTION(2003, "数据库请求错误"),

    SYSTEM_EXCEPTION(2004, "系统内部错误"),

    REDIS_EXCEPTION(3001, "缓存系统错误"),

    ERROR(5000, "未知错误");

    @Override
    public String toString() {
        return msg;
    }

    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
