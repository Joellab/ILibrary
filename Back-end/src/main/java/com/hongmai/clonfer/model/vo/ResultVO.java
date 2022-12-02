package com.hongmai.clonfer.model.vo;

import com.hongmai.clonfer.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义统一响应体, 相关用法可以参考文章：
 * https://mp.weixin.qq.com/s?__biz=MzkzMjE3NTA3Mg==&mid=2247484586&idx=1&sn=88f39689a53a024d2a3cd1d6101ebd60
 *
 * @author JiaweiWang
 */
@Getter
public class ResultVO<T> {
    /**
     * 状态码, 默认1000是成功
     */
    private int code;
    /**
     * 响应信息, 来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCodeEnum.SUCCESS, data);
    }

    public ResultVO(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":\"%s\"}", code, msg, data.toString());
    }
}
