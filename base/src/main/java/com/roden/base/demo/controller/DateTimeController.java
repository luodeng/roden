package com.roden.base.demo.controller;

import com.roden.base.demo.service.DateTimeService;
import com.roden.base.demo.domain.DateTimeDO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log
@RestController
@RequestMapping("date")
public class DateTimeController {
    @Autowired
    private DateTimeService dateTimeService;

    @RequestMapping("/list")
    List<DateTimeDO> list(DateTimeDO dateTimeDO){
        return dateTimeService.listAll();
    }
    @RequestMapping("/save")
    DateTimeDO save(DateTimeDO dateTimeDO){
        log.info("save date:{}"+ dateTimeDO.toString());
        dateTimeDO.setId(UUID.randomUUID().toString());
        dateTimeService.saveDateTime(dateTimeDO);
       return dateTimeDO;
    }
}
