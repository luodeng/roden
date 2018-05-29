package com.roden.base.demo.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class DateTimeDO {
    private String id;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat( pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    //@DateTimeFormat(pattern = "HH:mm:ss")
    //@JsonFormat( pattern = "HH:mm:ss")
    private LocalTime localTime;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
}
