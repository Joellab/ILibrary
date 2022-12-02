package com.hongmai.clonfer.service;

import com.hongmai.clonfer.model.domain.IlSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongmai.clonfer.model.vo.NextJobVO;

/**
* @author joelb
* @description 针对表【il_setting】的数据库操作Service
* @createDate 2022-12-01 16:31:56
*/
public interface IlSettingService extends IService<IlSetting> {
    String getLatestCommand();

    void setLatestCommand(String commandValue);
}
