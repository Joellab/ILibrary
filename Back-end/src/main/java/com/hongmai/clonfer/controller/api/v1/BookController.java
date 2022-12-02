package com.hongmai.clonfer.controller.api.v1;

import com.hongmai.clonfer.model.domain.LibBookRecord;
import com.hongmai.clonfer.service.LibBookRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final LibBookRecordService libBookRecordService;

    @GetMapping("/list")
    public List<LibBookRecord> list() {
        return libBookRecordService.list();
    }

}
