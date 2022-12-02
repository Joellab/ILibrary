package com.hongmai.clonfer.enums;

import lombok.Getter;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Getter
public enum InspectStatusEnum {

    INIT("INIT", "初始化"),

    PREPARING("PREPARING", "筹备中"),

    PROCESSING("PROCESSING", "进行中"),

    FINISH("FINISH", "已结束"),

    ARCHIVED("ARCHIVED", "已归档"),

    DISPATCHED("DISPATCHED", "已废弃"),

    ERROR("ERROR", "未知异常");

    private String code;
    private String msg;

    InspectStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
