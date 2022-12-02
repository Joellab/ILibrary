package com.hongmai.clonfer.model.vo;

import lombok.Data;

import java.util.Set;

/**
 * 角色分页对象
 *
 * @author JiaweiWang
 */
@Data
public class RolePageVO {
    private Long id;
    private String name;
    private Set<Long> resourceIds;
}