package com.roden.base.service;

import com.roden.base.dao.UserDAO;
import com.roden.base.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public UserDO getByUserName(String userName){
      return   userDAO.getByUserName(userName);
    }
    public List<UserDO> listAll(){
      return   userDAO.listAll();
    }
    public int countUser(){
        return userDAO.countUser();
    }
    public int saveUser(UserDO userDO){
        return userDAO.insertUser(userDO);
    }
    public int removeUser(String userId){
        return userDAO.deleteUser(userId);
    }
    public int updateUser(UserDO userDO){
        return userDAO.updateUser(userDO);
    }
}
