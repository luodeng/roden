package com.roden.base.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class UserDO {
    private String userId;
    private String userName;
    private LocalDate birthDay;
    private LocalTime now;
    private LocalDateTime createTime;
}
