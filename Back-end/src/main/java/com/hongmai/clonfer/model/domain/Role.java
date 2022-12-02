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
@TableName("role")
public class Role extends BaseDomain {
    /**
     * 名称
     */
    private String name;
}
