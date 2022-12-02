package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("resource")
public class Resource extends BaseDomain {
    /**
     * 路径
     */
    private String path;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型。0为菜单，1为接口
     */
    private Integer type;
}
