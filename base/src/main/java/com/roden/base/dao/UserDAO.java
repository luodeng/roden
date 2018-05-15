package com.roden.base.dao;

import com.roden.base.domain.UserDO;

import java.util.List;

public interface UserDAO {
   UserDO getByUserName(String userName);
   List<UserDO> listAll();
   int countUser();
   int insertUser(UserDO userDO);
   int deleteUser(String userId);
   int updateUser(UserDO userDO);


}
