package com.hongmai.clonfer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongmai.clonfer.model.domain.CpBaseOrginazation;
import com.hongmai.clonfer.model.domain.CpInspectPipeline;
import com.hongmai.clonfer.model.vo.NextJobVO;
import org.springframework.stereotype.Service;

public interface PipelineService extends IService<CpInspectPipeline> {
    NextJobVO getNextJob(String currentJob);
}
