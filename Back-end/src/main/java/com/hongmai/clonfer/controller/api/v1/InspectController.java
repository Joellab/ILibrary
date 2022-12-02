package com.hongmai.clonfer.controller.api.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongmai.clonfer.annotation.Auth;
import com.hongmai.clonfer.annotation.WebLog;
import com.hongmai.clonfer.enums.ResultCodeEnum;
import com.hongmai.clonfer.exception.ApiException;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.hongmai.clonfer.model.param.inspect.InspectParam;
import com.hongmai.clonfer.model.vo.InspectPageVO;
import com.hongmai.clonfer.model.vo.InspectVO;
import com.hongmai.clonfer.service.InspectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

/**
 * @author JiaweiWang
 * @date 2021/9/3
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/inspect")
@Auth(id = 1000, name = "会议管理")
public class InspectController {
    @Autowired
    private InspectService inspectService;

    @PostMapping
    @Auth(id = 1, name = "新增会议")
    @WebLog(description = "新增会议接口")
    public void createInspect(@RequestBody @Validated(InspectParam.CreateInspect.class) InspectParam param) {
        inspectService.createInspect(param);
    }


    @DeleteMapping
    @Auth(id = 2, name = "删除会议")
    @WebLog(description = "删除会议接口")
    public String deleteInspect(String[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            throw new ApiException(ResultCodeEnum.VALIDATE_FAILED);
        }
        inspectService.removeByIds(Arrays.asList(ids));
        return ResultCodeEnum.SUCCESS.toString();
    }

    @PutMapping
    @Auth(id = 3, name = "编辑会议")
    @WebLog(description = "编辑会议接口")
    public String updateInspect(@RequestBody @Validated(InspectParam.UpdateInspect.class) InspectParam param) {
        inspectService.updateInspect(param);
        return ResultCodeEnum.SUCCESS.toString();
    }

    @GetMapping("/page/{currentPage}")
    @Auth(id = 4, name = "查询会议列表")
    @WebLog(description = "查询会议列表接口")
    public IPage<InspectPageVO> getInspectPage(@PathVariable("currentPage") int currentPage) {
        Page<CpInspectRecord> inspectPage = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("gmt_create");
        orderItem.setAsc(false);
        inspectPage.setCurrent(currentPage).addOrder(orderItem);
        return inspectService.queryInspectPage(inspectPage);
    }

    @GetMapping("/detail/{inspectId}")
    @Auth(id = 5, name = "查询会议详情")
    @WebLog(description = "查询会议详情接口")
    public InspectVO getInspectDetail(@PathVariable("inspectId") String inspectId) {
        return inspectService.queryInspectDetail(inspectId);
    }

}
