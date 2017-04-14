package com.roden.mybatis.service.impl;

import javax.annotation.Resource;

import com.roden.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

import com.roden.mybatis.dao.IUserDao;
import com.roden.mybatis.pojo.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Override
	public int addUser(User user) {
		return userDao.insertSelective(user);
	}

}
