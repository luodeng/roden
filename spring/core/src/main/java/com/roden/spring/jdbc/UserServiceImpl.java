package com.roden.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {
	@Autowired
	BaseDao baseDao;
	
	public int add(User user) {
		String addSql = "insert into User(Id,Mobile,CreateTime) values(:id,:mobile,:createTime)";
		return baseDao.insert(addSql, user);
	}

	public int update(User user) {
		String editSql = "update User set mobile=:mobile,createTime=:createTime where id=:id";
		return baseDao.addOrUpdate(editSql, user);
	}

	public int delete(String userID) {
		String deleteSql = "delete from User where id= ? ";
		return baseDao.editObject(deleteSql, new Object[] { userID });
	}
	
	public User get(Integer id) {
		String getSql = "select * from User where id= ? ";
		User user = (User) baseDao.getObject(getSql, User.class,new Object[] { id });
		return user;
	}
	
	public List<User> getAll() {
		String getAllSql = "select * from User";
		return FillUpEntitySet(baseDao.queryForList(getAllSql, new Object[] {}));
	}

	protected List<User> FillUpEntitySet(List<Map<String, Object>> mpTable) {
		List<User> lstObj = new ArrayList<User>();
		for (int i = 0; i < mpTable.size(); i++) {
			lstObj.add(FillUpEntity(mpTable.get(i)));
		}
		return lstObj;
	}

	protected User FillUpEntity(Map<String, Object> mpRow) {
		User obj = new User();
		obj.setId((Integer) mpRow.get("id"));
		obj.setMobile((String) mpRow.get("mobile"));
		obj.setCreateTime((Date) mpRow.get("createTime"));
		return obj;
	}

}
