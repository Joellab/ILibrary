package com.hongmai.clonfer.controller.api.comm;

import com.hongmai.clonfer.annotation.Auth;
import com.hongmai.clonfer.annotation.WebLog;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.hongmai.clonfer.model.vo.NextJobVO;
import com.hongmai.clonfer.service.IlSettingService;
import com.hongmai.clonfer.service.PipelineService;
import com.hongmai.clonfer.service.QRReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JiaweiWang
 * @date 2022/12/1
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/comm/job")
@Auth(id = 1200, name = "Robot Management")
public class RobotController {
    @Autowired
    private PipelineService pipelineService;

    @GetMapping
    public NextJobVO nextJob(@RequestParam String current){
        System.out.println(current);
        return pipelineService.getNextJob(current);
    }

}
