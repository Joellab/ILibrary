package com.hongmai.clonfer.service;

import com.hongmai.clonfer.model.domain.LibBookRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author joelb
* @description 针对表【lib_book_record】的数据库操作Service
* @createDate 2022-11-29 23:53:21
*/
public interface LibBookRecordService extends IService<LibBookRecord> {
    void addNewBook(LibBookRecord libBookRecord);

}
