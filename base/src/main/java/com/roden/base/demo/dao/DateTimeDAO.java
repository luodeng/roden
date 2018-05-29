package com.roden.base.demo.dao;


import com.roden.base.demo.domain.DateTimeDO;

import java.util.List;

public interface DateTimeDAO {
    List<DateTimeDO> listAll();
    int insertDateTime(DateTimeDO dateTimeDO);
}
