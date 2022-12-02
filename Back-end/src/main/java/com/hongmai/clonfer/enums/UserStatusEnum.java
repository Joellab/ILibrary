package com.hongmai.clonfer.enums;

import lombok.Getter;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
@Getter
public enum UserStatusEnum {

    NORMAL("NORMAL", "正常"),

    BLOCKED("BLOCKED", "封禁");

    private String code;
    private String msg;

    UserStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
