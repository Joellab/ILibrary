package com.hongmai.clonfer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongmai.clonfer.model.domain.CpBaseOrginazation;
import com.hongmai.clonfer.model.vo.InspectVO;
import com.hongmai.clonfer.model.vo.OrginazationPageVO;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
public interface OrganizationService extends IService<CpBaseOrginazation> {
    /**
     *
     * @param page
     * @return
     */
    IPage<OrginazationPageVO> queryOrganizationPage(Page<CpBaseOrginazation> page);

    /**
     *
     * @param inspectId
     * @return
     */
    InspectVO queryOrganizationDetail(String inspectId);

}
