package com.hongmai.clonfer.model.vo;

import com.hongmai.clonfer.model.domain.CpInspectRecord;
import lombok.Data;


import java.util.Date;
import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Data
public class InspectVO {

    private String uuid;

    private String title;

    private String subTitle;

    private String organization;

    private String guarantor;

    private String location;

    private Date startTime;

    private Date endTime;

    private String status;

    private String creator;

    private List<InspectPipelineVO> pipeline;

    private Date gmtCreate;

    private Date gmtModified;

    private String extSetting;

    public static InspectVO convertDOToVO(CpInspectRecord cpInspectRecord) {

        InspectVO inspectVO = new InspectVO();
        inspectVO.setUuid(cpInspectRecord.getUuid());
        inspectVO.setTitle(cpInspectRecord.getTitle());
        inspectVO.setSubTitle(cpInspectRecord.getSubTitle());
        inspectVO.setGuarantor(cpInspectRecord.getGuarantor());
        inspectVO.setOrganization(cpInspectRecord.getOrganization());
        inspectVO.setLocation(cpInspectRecord.getLocation());
        inspectVO.setStatus(cpInspectRecord.getStatus());
        inspectVO.setCreator(cpInspectRecord.getCreator());
        inspectVO.setStartTime(cpInspectRecord.getStartTime());
        inspectVO.setEndTime(cpInspectRecord.getEndTime());
        inspectVO.setGmtCreate(cpInspectRecord.getGmtCreate());
        inspectVO.setGmtModified(cpInspectRecord.getGmtModified());
        inspectVO.setExtSetting(cpInspectRecord.getExtSetting());

        return inspectVO;
    }
}
