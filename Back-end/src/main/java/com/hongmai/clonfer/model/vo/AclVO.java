package com.hongmai.clonfer.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
@Accessors(chain = true)
public class AclVO {

    private String code;

    private String action;

    private String subject;

    private List<Integer> resourceId;
}
