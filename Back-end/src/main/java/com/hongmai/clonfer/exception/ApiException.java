package com.hongmai.clonfer.exception;

import com.hongmai.clonfer.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Getter
public class ApiException extends RuntimeException {
    private final String msg;
    private final ResultCodeEnum resultCodeEnum;

    public ApiException() {
        this(ResultCodeEnum.FAILED);
    }

    public ApiException(String msg) {
        this(ResultCodeEnum.FAILED, msg);
    }

    public ApiException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum, resultCodeEnum.getMsg());
    }

    public ApiException(ResultCodeEnum resultCodeEnum, String msg) {
        super(msg);
        this.resultCodeEnum = resultCodeEnum;
        this.msg =  msg;
    }
}
