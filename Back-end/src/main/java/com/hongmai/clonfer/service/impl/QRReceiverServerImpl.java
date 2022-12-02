package com.hongmai.clonfer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongmai.clonfer.model.domain.LibBookRecord;
import com.hongmai.clonfer.service.LibBookRecordService;
import com.hongmai.clonfer.service.QRReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JiaweiWang
 * @date 2022/11/27
 * @description
 */
@Service
public class QRReceiverServerImpl implements QRReceiverService {
    @Autowired
    LibBookRecordService libBookRecordService;

    @Override
    public void QRScan(String scanResult) {

        scanResult = JSON.parse(scanResult).toString();
        JSONArray jsonArray = JSONArray.parseArray(scanResult);

        String UUID;
        String category;
        String type;
        String name;
        for (Object object : jsonArray) {
            LibBookRecord libBookRecord = new LibBookRecord();

            JSONObject jsonObject = JSONObject.parseObject((String) object);
            UUID = jsonObject.getString("UUID");
            category = jsonObject.getString("Category");
            type = jsonObject.getString("Type");
            name = jsonObject.getString("Name");

            if (type.equals("Book")) {
                libBookRecord.setUuid(UUID);
                libBookRecord.setStatus("NORMAL");
                libBookRecord.setName(name);
                libBookRecord.setCategory(category);
                libBookRecord.setLastScan(String.valueOf(System.currentTimeMillis()));
                System.out.println(String.valueOf(System.currentTimeMillis()));
                libBookRecordService.addNewBook(libBookRecord);
            }


        }
    }
}
