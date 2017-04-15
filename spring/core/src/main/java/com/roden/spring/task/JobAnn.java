package com.roden.spring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  
public class JobAnn {
	
	public static Logger log = LoggerFactory.getLogger(JobAnn.class);
	
    @Scheduled(fixedDelay = 5*1000) //(1800000)    30分钟
    public void work()
    {
    	System.out.println("spring定时任务注解配置方式:fixedDelay---"+new Date());
    	
    } 
    
    @Scheduled(cron="0 1 0 * * ? ") 
    public void task()
    {
    	System.out.println("spring定时任务注解配置方式:cron---"+new Date());
    	
    } 
}