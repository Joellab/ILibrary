package com.hongmai.clonfer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongmai.clonfer.model.param.inspect.InspectParam;
import com.hongmai.clonfer.model.vo.InspectPageVO;
import com.hongmai.clonfer.model.vo.InspectVO;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
public interface InspectService extends IService<CpInspectRecord> {
    /**
     *
     * @param param
     */
    void createInspect(InspectParam param);

    /**
     *
     * @param param
     */
    void updateInspect(InspectParam param);

    /**
     *
     * @param page
     * @return
     */
    IPage<InspectPageVO> queryInspectPage(Page<CpInspectRecord> page);

    /**
     *
     * @param inspectId
     * @return
     */
    InspectVO queryInspectDetail(String inspectId);

}
