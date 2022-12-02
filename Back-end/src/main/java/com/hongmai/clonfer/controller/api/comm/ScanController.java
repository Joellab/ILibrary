package com.hongmai.clonfer.controller.api.comm;

import com.hongmai.clonfer.annotation.Auth;
import com.hongmai.clonfer.annotation.WebLog;
import com.hongmai.clonfer.model.domain.CpInspectRecord;
import com.hongmai.clonfer.service.QRReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JiaweiWang
 * @date 2021/9/3
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/comm/scan")
@Auth(id = 1000, name = "QRScan Management")
public class ScanController {
    @Autowired
    private QRReceiverService qrReceiverService;

    @PostMapping
    @WebLog(description = "QR Code Scan Result Login")
    public void QRScanResult(@RequestBody String QRResult) {
        qrReceiverService.QRScan(QRResult);
    }
}
