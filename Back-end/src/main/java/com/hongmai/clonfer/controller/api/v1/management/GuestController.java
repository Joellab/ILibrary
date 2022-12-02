package com.hongmai.clonfer.controller.api.v1.management;

import com.hongmai.clonfer.annotation.Auth;
import com.hongmai.clonfer.annotation.WebLog;
import com.hongmai.clonfer.model.param.inspect.InspectParam;
import com.hongmai.clonfer.service.InspectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author JiaweiWang
 * @date 2021/12/27
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/guest/inspect")
@Auth(id = 2000, name = "嘉宾管理")
public class GuestController {
    @Autowired
    private InspectService inspectService;

    @PostMapping
    @Auth(id = 1, name = "新增嘉宾")
    @WebLog(description = "新增嘉宾接口")
    public void createInspect(@RequestBody @Validated(InspectParam.CreateInspect.class) InspectParam param) {
        inspectService.createInspect(param);
    }
}

