package com.roden.base.demo.service;

import com.roden.base.demo.dao.DateTimeDAO;
import com.roden.base.demo.domain.DateTimeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateTimeService {
    @Autowired
    private  DateTimeDAO dateTimeDAO;

    public List<DateTimeDO> listAll(){
        return   dateTimeDAO.listAll();
    }

    public int saveDateTime(DateTimeDO dateTimeDO){
        return dateTimeDAO.insertDateTime(dateTimeDO);
    }
}
