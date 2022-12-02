package com.hongmai.clonfer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.enums.InspectStatusEnum;
import com.hongmai.clonfer.enums.NodeStatusEnum;
import com.hongmai.clonfer.exception.ApiException;
import com.hongmai.clonfer.model.domain.CpBaseOrginazation;
import com.hongmai.clonfer.model.domain.CpInspectPipeline;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.hongmai.clonfer.model.param.SelectTemplateParam;
import com.hongmai.clonfer.model.param.UserPageParam;
import com.hongmai.clonfer.model.param.inspect.InspectParam;
import com.hongmai.clonfer.model.param.inspect.InspectPipelineParam;
import com.hongmai.clonfer.model.vo.InspectPageVO;
import com.hongmai.clonfer.model.vo.InspectPipelineVO;
import com.hongmai.clonfer.model.vo.InspectVO;
import com.hongmai.clonfer.service.InspectService;
import com.hongmai.clonfer.mapper.CpInspectRecordMapper;
import com.hongmai.clonfer.service.OrganizationService;
import com.hongmai.clonfer.service.PipelineService;
import com.hongmai.clonfer.util.DateUtil;
import com.hongmai.clonfer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Service
public class InspectServiceImpl extends ServiceImpl<CpInspectRecordMapper, CpInspectRecord> implements InspectService {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    PipelineService pipelineService;
    @Autowired
    UserUtil userUtil;
    /**
     *
     * @param param
     */
    @Override
    public void createInspect(InspectParam param) {

        if (lambdaQuery().eq(CpInspectRecord::getTitle, param.getTitle()).one() != null) {
            throw new ApiException("会议名称已存在");
        }
        CpInspectRecord cpInspectRecord = new CpInspectRecord();
        cpInspectRecord.setTitle(param.getTitle());
        cpInspectRecord.setSubTitle(param.getSubTitle());
        cpInspectRecord.setOrganization(param.getOrganization());
        cpInspectRecord.setGuarantor(JSON.toJSONString(param.getGuarantor()));
        cpInspectRecord.setLocation(JSON.toJSONString(param.getLocation()));
        cpInspectRecord.setStartTime(param.getStartTime());
        cpInspectRecord.setEndTime(param.getEndTime());
        cpInspectRecord.setExtSetting(param.getExtSetting());

        cpInspectRecord.setUuid(UUID.randomUUID().toString());
        cpInspectRecord.setStatus(InspectStatusEnum.INIT.getCode());
        cpInspectRecord.setVersion(0);
        cpInspectRecord.setCreator(UserUtil.getCurrentUserName());
        cpInspectRecord.setOperator(UserUtil.getCurrentUserName());
        cpInspectRecord.setGmtCreate(DateUtil.getCurrentTime());
        cpInspectRecord.setGmtModified(DateUtil.getCurrentTime());

        for(InspectPipelineParam inspectPipelineParam : param.getPipeline()) {
            CpInspectPipeline cpInspectPipeline = new CpInspectPipeline();
            cpInspectPipeline.setUuid(UUID.randomUUID().toString());
            cpInspectPipeline.setInspectRecordUuid(cpInspectRecord.getUuid());
            cpInspectPipeline.setSequence(inspectPipelineParam.getId());
            cpInspectPipeline.setName(inspectPipelineParam.getName());
            cpInspectPipeline.setAction(JSON.toJSONString(inspectPipelineParam.getAction()));
            //cpInspectPipeline.setAppliedPopulation(JSON.toJSONString(inspectPipelineParam.getAppliedPopulation()));
            cpInspectPipeline.setOwner(inspectPipelineParam.getOwner().getUuid());
            cpInspectPipeline.setStatus(NodeStatusEnum.INIT.getCode());
            pipelineService.save(cpInspectPipeline);
        }

        save(cpInspectRecord);
    }

    /**
     *
     * @param param
     */
    @Override
    public void updateInspect(InspectParam param) {
        CpInspectRecord cpInspectRecord = new CpInspectRecord();
        cpInspectRecord.setUuid(param.getUuid());
        cpInspectRecord.setTitle(param.getTitle());
        cpInspectRecord.setSubTitle(param.getSubTitle());
        cpInspectRecord.setOrganization(param.getOrganization());
        cpInspectRecord.setGuarantor(JSON.toJSONString(param.getGuarantor()));
        cpInspectRecord.setLocation(JSON.toJSONString(param.getLocation()));
        cpInspectRecord.setStartTime(param.getStartTime());
        cpInspectRecord.setEndTime(param.getEndTime());
        cpInspectRecord.setExtSetting(param.getExtSetting());

        cpInspectRecord.setOperator(UserUtil.getCurrentUserName());
        cpInspectRecord.setGmtModified(DateUtil.getCurrentTime());

        updateById(cpInspectRecord);
    }

    /**
     *
     * @param page
     * @return
     */
    @Override
    public IPage<InspectPageVO> queryInspectPage(Page<CpInspectRecord> page) {
        QueryWrapper queryWrapperOrg = new QueryWrapper();
        queryWrapperOrg.eq("organization", userUtil.getCurrentUserDetails().getOrginazationUuid());
        IPage<CpInspectRecord> pages = baseMapper.selectPage(page, queryWrapperOrg);
        IPage<InspectPageVO> tranferPages = pages.convert(CpInspectRecord -> BeanUtil.copyProperties(CpInspectRecord, InspectPageVO.class));
        for(InspectPageVO inspectPageVO : tranferPages.getRecords()) {
            QueryWrapper<CpBaseOrginazation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uuid", inspectPageVO.getOrganization());
            String organizationName;
            try {
                organizationName = organizationService.getOne(queryWrapper).getName();
            } catch (Exception e) {
                organizationName = "组织不存在";
            }
            inspectPageVO.setOrganization(organizationName);
        }
        return tranferPages;
    }

    /**
     *
     * @param inspectId
     * @return
     */
    @Override
    public InspectVO queryInspectDetail(String inspectId) {
        CpInspectRecord inspectRecord = baseMapper.selectById(inspectId);
        QueryWrapper<CpInspectPipeline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cp_inspect_record_uuid", inspectRecord.getUuid());
        List<CpInspectPipeline> cpInspectPipelines = pipelineService.list(queryWrapper);
        InspectVO inspectVO = InspectVO.convertDOToVO(inspectRecord);
        List<InspectPipelineVO> inspectPipelineVOS = new ArrayList<>();
        for(CpInspectPipeline cpInspectPipeline : cpInspectPipelines) {
            InspectPipelineVO inspectPipelineVO = new InspectPipelineVO();
            inspectPipelineVO.setUuid(cpInspectPipeline.getUuid());
            inspectPipelineVO.setName(cpInspectPipeline.getName());
            inspectPipelineVO.setSequence(cpInspectPipeline.getSequence());
            UserPageParam userPageParam = new UserPageParam();
            userPageParam.setFullname(userUtil.getSpecificUserDetailsByUuid(cpInspectPipeline.getOwner()).getFullname());
            userPageParam.setUuid(cpInspectPipeline.getOwner());
            inspectPipelineVO.setOwner(userPageParam);
            inspectPipelineVO.setAction(JSON.parseObject(cpInspectPipeline.getAction(), SelectTemplateParam.class));
            inspectPipelineVO.setAppliedPopulation(JSON.parseArray(cpInspectPipeline.getCoordinate(), SelectTemplateParam.class));
            inspectPipelineVOS.add(inspectPipelineVO);
        }

        inspectVO.setPipeline(inspectPipelineVOS);
        return inspectVO;
    }

    public static class PipelineServiceImpl {
    }
}




