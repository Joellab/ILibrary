package com.hongmai.clonfer.model.vo;

import lombok.Data;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public class InspectPageVO {

    /**
     * 编号
     */
    private String uuid;

    /**
     * 会议标题
     */
    private String title;

    /**
     * 会议标题
     */
    private String subTitle;

    /**
     * 保障阵型
     */
    private String organization;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 当前状态
     */
    private String status;
}
