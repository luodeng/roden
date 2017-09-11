package com.roden.mybatis.service.impl;

import javax.annotation.Resource;

import com.roden.mybatis.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.roden.mybatis.dao.IUserDao;
import com.roden.mybatis.pojo.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Override
	public int addUser(User user) {
		log.info("测试插入返回自增主键");
		log.info("insert before:{}",user);
		int count= userDao.insertAndGetId(user);
		log.info("insert after:{}",user);
		return count;
	}

}
