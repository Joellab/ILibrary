package com.hongmai.clonfer.controller.api.v1;

import com.hongmai.clonfer.model.domain.CpInspectPipeline;
import com.hongmai.clonfer.model.domain.LibBookRecord;
import com.hongmai.clonfer.service.IlSettingService;
import com.hongmai.clonfer.service.LibBookRecordService;
import com.hongmai.clonfer.service.PipelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pipeline")
@RequiredArgsConstructor
public class PipelineController {
    private final LibBookRecordService libBookRecordService;

    private final PipelineService pipelineService;

    private final IlSettingService ilSettingService;

    @GetMapping("/list")
    public List<CpInspectPipeline> list() {
        return pipelineService.list();
    }

    @GetMapping("/start")
    public void start() {
        ilSettingService.setLatestCommand("NEWJOB|0eee8d6a-e880-45e9-86eb-a2bdcb224333");
    }

    @GetMapping("/stop")
    public void stop() {
        ilSettingService.setLatestCommand("STOP");
    }

}
