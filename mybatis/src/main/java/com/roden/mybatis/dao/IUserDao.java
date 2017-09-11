package com.roden.mybatis.dao;

import com.roden.mybatis.pojo.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*  华丽的分割线，以上代码为自动生成        */

    int insertAndGetId(User record);
}