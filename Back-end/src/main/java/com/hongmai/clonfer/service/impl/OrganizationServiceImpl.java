package com.hongmai.clonfer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.mapper.CpBaseOrginazationMapper;
import com.hongmai.clonfer.model.domain.CpBaseOrginazation;
import com.hongmai.clonfer.model.vo.InspectVO;
import com.hongmai.clonfer.model.vo.OrginazationPageVO;
import com.hongmai.clonfer.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<CpBaseOrginazationMapper, CpBaseOrginazation> implements OrganizationService {

    @Override
    public IPage<OrginazationPageVO> queryOrganizationPage(Page<CpBaseOrginazation> page) {
        IPage<CpBaseOrginazation> pages = baseMapper.selectPage(page, null);
        IPage<OrginazationPageVO> tranferPages = pages.convert(CpBaseOrginazation -> BeanUtil.copyProperties(CpBaseOrginazation, OrginazationPageVO.class));

        return tranferPages;
    }

    @Override
    public InspectVO queryOrganizationDetail(String inspectId) {
        return null;
    }
}
