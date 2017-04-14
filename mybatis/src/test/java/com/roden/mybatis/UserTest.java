package com.roden.mybatis;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import com.roden.mybatis.pojo.User;
import com.roden.mybatis.service.IUserService;
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"})
public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);
	@Resource
	private IUserService userService;

//	@Before
//	public void before() {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void testGet() {
		User user = userService.getUserById(1);
		System.out.println(user.getUserName());
		log.info("值："+user.getUserName());
		log.info(JSON.toJSONString(user));
	}
}
