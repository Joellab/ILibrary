package com.hongmai.clonfer.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.mapper.CpBaseResourcesMapper;
import com.hongmai.clonfer.model.domain.CpBaseResource;
import com.hongmai.clonfer.model.domain.Resource;
import com.hongmai.clonfer.service.ResourceService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends ServiceImpl<CpBaseResourcesMapper, CpBaseResource> implements ResourceService {

    @Override
    public Set<Long> getIdsByUserId(Long userId) {
        //return baseMapper.selectIdsByUserId(userId);
        return null;
    }

    @Override
    public void insertResources(Collection<CpBaseResource> resources) {
        if (Collections.isEmpty(resources)) {
            return;
        }
        // 再新增接口类型的资源
        baseMapper.insertResources(resources);

    }

    @Override
    public void deleteResourceByType(int type) {
        // 先删除所有接口类型的资源
        LambdaUpdateWrapper<CpBaseResource> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CpBaseResource::getType, type);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<Resource> getResourcesByUserId(Long userId) {
        return null;
        //return baseMapper.selectListByUserId(userId);
    }
}