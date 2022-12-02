package com.hongmai.clonfer.model.param.guest;

import lombok.Data;

/**
 * @author JiaweiWang
 * @date 2021/12/27
 * @description
 */
@Data
public class GuestParam {
    /**
     * 头像链接
     */
    private String picImgUrl;

    /**
     * 基础信息
     */
    private GuestBasic basic;
}
