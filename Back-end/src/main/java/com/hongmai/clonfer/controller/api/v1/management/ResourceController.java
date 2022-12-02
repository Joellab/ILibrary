package com.hongmai.clonfer.controller.api.v1.management;

import com.hongmai.clonfer.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/API/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /*
    @GetMapping("/list")
    public List<Resource> getList() {
        return resourceService.list();
    }
    */
}
