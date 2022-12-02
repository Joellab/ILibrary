package com.hongmai.clonfer.enums;

import lombok.Getter;

/**
 * @author JiaweiWang
 * @date 2021/9/13
 * @description
 */
@Getter
public enum NodeStatusEnum {

    INIT("INIT", "初始化"),

    PREPARING("PREPARING", "进行中"),

    FINISH("FINISH", "已结束"),

    ARCHIVED("ARCHIVED", "已归档"),

    ERROR("ERROR", "未知异常");

    private String code;
    private String msg;

    NodeStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
