package com.roden.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.roden.base.dao")
public class SampleController {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}