package com.hongmai.clonfer.model.param.guest;

import lombok.Data;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author JiaweiWang
 * @date 2021/12/27
 * @description
 */
@Data
public class GuestBasic {
    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private String dateOfBirth;

    /**
     * 性别
     */
    private String gender;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 语言偏好
     */
    private String langPref;

    /**
     * 电话号码
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系偏好
     */
    private String contactPref;

    /**
     * 企业名称
     */
    private String coopName;

    /**
     * 职位
     */
    private String coopPosi;

    /**
     * 职级
     */
    private String coopLevel;

    /**
     * 对接人
     */
    private String primaryContact;

    /**
     * 经手人
     */
    private String procContact;
}
