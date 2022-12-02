package com.hongmai.clonfer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.model.domain.CpInspectPipeline;
import com.hongmai.clonfer.model.domain.IlSetting;
import com.hongmai.clonfer.model.vo.NextJobVO;
import com.hongmai.clonfer.service.IlSettingService;
import com.hongmai.clonfer.mapper.IlSettingMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
* @author joelb
* @description 针对表【il_setting】的数据库操作Service实现
* @createDate 2022-12-01 16:31:56
*/
@Service
public class IlSettingServiceImpl extends ServiceImpl<IlSettingMapper, IlSetting>
    implements IlSettingService{

    @Override
    public String getLatestCommand() {
        IlSetting ilSetting = new IlSetting();
        QueryWrapper<IlSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "CMD");
        List<IlSetting> ilSettings = baseMapper.selectList(queryWrapper);
        return ilSettings.get(0).getValue();
    }

    @Override
    public void setLatestCommand(String commandValue) {
        QueryWrapper<IlSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "CMD");
        List<IlSetting> ilSettings = baseMapper.selectList(queryWrapper);
        IlSetting ilSetting = new IlSetting();
        ilSetting.setUuid(ilSettings.get(0).getUuid());
        ilSetting.setName("CMD");
        ilSetting.setValue(commandValue);
        saveOrUpdate(ilSetting);
    }
}




