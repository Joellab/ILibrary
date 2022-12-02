package com.hongmai.clonfer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongmai.clonfer.model.domain.LibBookRecord;
import com.hongmai.clonfer.service.LibBookRecordService;
import com.hongmai.clonfer.mapper.LibBookRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author joelb
* @description 针对表【lib_book_record】的数据库操作Service实现
* @createDate 2022-11-29 23:53:21
*/
@Service
public class LibBookRecordServiceImpl extends ServiceImpl<LibBookRecordMapper, LibBookRecord> implements LibBookRecordService{
    public void addNewBook(LibBookRecord libBookRecord) {
        saveOrUpdate(libBookRecord);
    }
}




