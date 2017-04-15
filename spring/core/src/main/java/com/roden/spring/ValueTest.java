package com.roden.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@PropertySource(value = "classpath:jdbc.properties")
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ComponentScan(basePackages="com.roden.spring.task")
@Component
public class ValueTest {
	@Value("${driver}")
	public String username;	

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Resource
	public ValueTest valueTest;

	@Test
	public void xmlConfigureTest() throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/roden/spring/task/spring-task.xml");
		ValueTest vt = (ValueTest) context.getBean("valueTest");
		System.out.println(vt.username);
	}
	
	@Test
	public void test2() throws InterruptedException {
		
		System.out.println(valueTest.username);
	}	
}
/*
 * 
 <!-- <bean id="appProperty"  class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	    <property name="locations">
	        <array>
	            <value>classpath:jdbc.properties</value>
	        </array>
	    </property>
	</bean> -->
    
   <!--  <context:property-placeholder location="classpath:jdbc.properties,classpath:jdbc.properties"/> -->
    
   <!--  <util:properties id="jetProperties"  location="classpath:jdbc.properties" /> -->
 * 
 */
