package com.roden.base.controller;

import com.github.pagehelper.PageHelper;
import com.roden.base.domain.UserDO;
import com.roden.base.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Log
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserInfo")
    UserDO getUserInfo(){
        log.info("getUserInfo");
        return userService.getByUserName("roden");
    }

    @GetMapping("/listUser")
    List<UserDO> listUser(){
        PageHelper.startPage(1, 2);
        return userService.listAll();
    }

    @GetMapping("/countUser")
    int countUser(){
        return userService.countUser();
    }

    @RequestMapping("/saveUser")
    int saveUser(UserDO userDO){
        log.info("save method");
        log.info("userDO:"+userDO);
        userDO=new UserDO();
        userDO.setUserId(UUID.randomUUID().toString().replace("-",""));
        userDO.setUserName("roden");
        userDO.setBirthDay(LocalDate.of(1991,4,23));
        userDO.setNow(LocalTime.now());
        userDO.setCreateTime(LocalDateTime.now());
        return  userService.saveUser(userDO);
    }
    @RequestMapping("/removeUser")
    int removeUser(){
        return  userService.removeUser("");
    }
    @PostMapping("/updateUser")
    int updateUser(){
        log.info("update method");
        UserDO userDO=new UserDO();
        userDO.setUserId(UUID.randomUUID().toString());
        userDO.setUserName("roden");
        userDO.setBirthDay(LocalDate.of(1991,4,23));
        userDO.setNow(LocalTime.now());
        userDO.setCreateTime(LocalDateTime.now());
        return  userService.updateUser(userDO);
    }

}
