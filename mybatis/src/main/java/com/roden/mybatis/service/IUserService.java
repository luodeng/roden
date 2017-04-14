package com.roden.mybatis.service;

import com.roden.mybatis.pojo.User;

public interface IUserService {
	public User getUserById(int userId);
	public int addUser(User user);
}
