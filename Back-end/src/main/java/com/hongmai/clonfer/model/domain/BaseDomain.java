package com.hongmai.clonfer.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public abstract class BaseDomain {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
}
