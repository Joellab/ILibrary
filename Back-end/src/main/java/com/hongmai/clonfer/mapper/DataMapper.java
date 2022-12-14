package com.hongmai.clonfer.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongmai.clonfer.model.domain.Data;
import com.hongmai.clonfer.model.vo.DataPageVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Repository
public interface DataMapper extends BaseMapper<Data> {
    /**
     * 查询数据分页信息
     * @param page 分页条件
     * @param wrapper 查询条件
     * @return 分页对象
     */
    IPage<DataPageVO> selectPage(Page<DataPageVO> page, @Param(Constants.WRAPPER) Wrapper<DataPageVO> wrapper);
}
