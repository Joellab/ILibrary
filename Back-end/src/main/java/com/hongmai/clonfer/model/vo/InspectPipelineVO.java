package com.hongmai.clonfer.model.vo;

import com.hongmai.clonfer.model.param.SelectTemplateParam;
import com.hongmai.clonfer.model.param.UserPageParam;
import lombok.Data;

import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/13
 * @description
 */
@Data
public class InspectPipelineVO {

    private String uuid;

    private Integer sequence;

    private String name;

    private SelectTemplateParam action;

    private UserPageParam owner;

    private List<SelectTemplateParam> appliedPopulation;

}
