package com.hongmai.clonfer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.mapper.CpInspectPipelineMapper;
import com.hongmai.clonfer.model.domain.CpInspectPipeline;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.hongmai.clonfer.model.vo.NextJobVO;
import com.hongmai.clonfer.service.IlSettingService;
import com.hongmai.clonfer.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author joelb
* @description 针对表【cp_inspect_pipeline】的数据库操作Service实现
* @createDate 2022-12-01 14:48:01
*/
@Service
public class PipelineServiceImpl extends ServiceImpl<CpInspectPipelineMapper, CpInspectPipeline> implements PipelineService {

    @Autowired
    IlSettingService ilSettingService;

    @Override
    public NextJobVO getNextJob(String currentJob) {
        CpInspectPipeline cpInspectPipeline = new CpInspectPipeline();
        NextJobVO nextJobVO = new NextJobVO();
        QueryWrapper<CpInspectPipeline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", currentJob);
        List<CpInspectPipeline> cpInspectPipelines = baseMapper.selectList(queryWrapper);

        if(currentJob.equals("INIT")) {
            nextJobVO.setUuid("INIT");
            nextJobVO.setAction("STANDBY");
            return nextJobVO;
        }

        if(cpInspectPipelines.get(0).getNextNodeUuid().equals("NULL")) {
            nextJobVO.setUuid(cpInspectPipelines.get(0).getUuid());
            nextJobVO.setAction("STANDBY");

            QueryWrapper<CpInspectPipeline> queryWrapperReset = new QueryWrapper<>();
            queryWrapperReset.eq("status", "DONE");
            cpInspectPipelines = baseMapper.selectList(queryWrapperReset);

            for(CpInspectPipeline cpInspectPipelineReset : cpInspectPipelines) {
                cpInspectPipelineReset.setStatus("INIT");
                saveOrUpdate(cpInspectPipelineReset);
            }
            ilSettingService.setLatestCommand("STOP");

            return nextJobVO;
        }

        cpInspectPipeline.setUuid(cpInspectPipelines.get(0).getUuid());
        cpInspectPipeline.setStatus("DONE");
        updateById(cpInspectPipeline);

        QueryWrapper<CpInspectPipeline> queryWrapperNext = new QueryWrapper<>();
        queryWrapperNext.eq("uuid", cpInspectPipelines.get(0).getNextNodeUuid());
        cpInspectPipelines = baseMapper.selectList(queryWrapperNext);

        cpInspectPipeline.setUuid(cpInspectPipelines.get(0).getUuid());
        cpInspectPipeline.setStatus("In Progress");
        updateById(cpInspectPipeline);

        cpInspectPipeline = cpInspectPipelines.get(0);


        nextJobVO.setUuid(cpInspectPipeline.getUuid());
        nextJobVO.setAction(cpInspectPipeline.getAction());
        System.out.println(cpInspectPipeline.getCoordinate());
        String[] coordinates = cpInspectPipeline.getCoordinate().split(",");
        nextJobVO.setPosiX(coordinates[0]);
        nextJobVO.setPosiY(coordinates[1]);
        nextJobVO.setPosiZ(coordinates[2]);
        nextJobVO.setOriX(coordinates[3]);
        nextJobVO.setOriY(coordinates[4]);
        nextJobVO.setOriZ(coordinates[5]);
        nextJobVO.setOriW(coordinates[6]);

        return nextJobVO;
    }
}




