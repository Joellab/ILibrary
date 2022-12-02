package com.hongmai.clonfer.model.param.inspect;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/3
 * @description
 */
@Data
public class InspectParam {
    @NotNull(message = "会议UUID不能为空", groups = {UpdateInspect.class, DeleteInspect.class} )
    private String uuid;

    @NotNull(message = "会议标题不能为空", groups = {CreateInspect.class} )
    @Length(min = 4, max = 20, message = "会议标题长度为4至20位", groups = {CreateInspect.class})
    private String title;

    @NotNull(message = "会议副标题不能为空", groups = {CreateInspect.class} )
    private String subTitle;

    @NotNull(message = "会议举办地不能为空", groups = {CreateInspect.class} )
    private InspectLocationParam location;

    @NotNull(message = "会议企业不能为空", groups = {CreateInspect.class} )
    private String organization;

    @NotNull(message = "会议归属不能为空", groups = {CreateInspect.class} )
    private InspectGuarantorParam guarantor;

    @Valid
    @NotEmpty(message = "流程人群不能为空", groups = {CreateInspect.class} )
    private List<InspectPipelineParam> pipeline;

    @NotNull(message = "会议开始时间不能为空", groups = {CreateInspect.class} )
    private Date startTime;

    @NotNull(message = "会议结束时间不能为空", groups = {CreateInspect.class} )
    private Date endTime;

    private String extSetting;

    public interface CreateInspect {}

    public interface UpdateInspect {}

    public interface DeleteInspect {}
}
