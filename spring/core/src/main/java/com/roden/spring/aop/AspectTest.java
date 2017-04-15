package com.roden.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectTest {   
    @Test       
    public void xmlConfigureTest(){
    	 ApplicationContext context = new ClassPathXmlApplicationContext("aop/aop_applicatioonContext.xml");
         AspectBusiness aspectBusiness = (AspectBusiness) context.getBean("aspectBusiness");
         aspectBusiness.delete("猫");
    }
    @Test
    public void annConfigureTest(){
    	 ApplicationContext context = new ClassPathXmlApplicationContext("aop/aop_applicatioonContext2.xml");
         AspectBusiness aspectBusiness = (AspectBusiness) context.getBean("aspectBusiness");
         aspectBusiness.delete("猫");
    }

}