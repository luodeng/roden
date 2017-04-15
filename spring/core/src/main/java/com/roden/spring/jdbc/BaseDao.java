package com.roden.spring.jdbc;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	private Log log = LogFactory.getLog(BaseDao.class);
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int insert(String sql, Object obj) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource ps=new BeanPropertySqlParameterSource(obj);//从user中取出数据，与sql语句中一一对应将数据换进去
		new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, ps,keyHolder);
		if (keyHolder.getKey() == null) {
			return 0;
		}
		return keyHolder.getKey().intValue();
	}

	public Map<String, Object> insertReturnKeys(String sql, Object obj) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		new NamedParameterJdbcTemplate(jdbcTemplate).update(sql, new BeanPropertySqlParameterSource(obj),keyHolder);
		return keyHolder.getKeys();

	}

	@SuppressWarnings("unchecked")
	public int GetIncrementID(final String generatorKey, final int iniValue)throws Exception {
		String procedure = "{call GetIncrementID(?,?,?)}";
		int retVal = 0;
		try {
			retVal = (Integer) jdbcTemplate.execute(procedure,new CallableStatementCallback() {
						public Object doInCallableStatement(CallableStatement cs)
								throws SQLException, DataAccessException {
							cs.setString(1, generatorKey);// 输入参数
							cs.setInt(2, iniValue);// 输入参数
							cs.registerOutParameter(3, Types.INTEGER); // 输出参数
							cs.execute();
							return cs.getInt(3);
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public Integer addOrUpdate(String sql, Object obj) {
		Integer id = 0;
		try {
			id = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			log.info(e);
		}
		return id;
	}

	public int editObject(String sql, Object[] obj) {
		int index = 0;
		try {
			index = jdbcTemplate.update(sql, obj);
		} catch (DataAccessException e) {
			log.info(e);
		}
		return index;
	}

	// 查询表数量
	@SuppressWarnings("deprecation")
	public int queryForInt(String sql, Object[] obj) {
		try {
			Number number = jdbcTemplate.queryForObject(sql, obj, Integer.class);
			return (number != null ? number.intValue() : 0);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	// 查询表List
	public List<Map<String, Object>> queryForList(String sql, Object[] obj) {
		try {
			return jdbcTemplate.queryForList(sql, obj);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Map<String, Object>>();
		}
	}

	// 查询表map
	public Map<String, Object> queryForMap(String sql, Object[] obj) {
		try {
			return jdbcTemplate.queryForMap(sql, obj);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// 分页查询
	public List<Map<String, Object>> queryForPage(PageList pageList,String fields, String fromTable, String whereClause, String sortBy,Object[] objArr) {
		int limitStart = (pageList.getCurrentPage() - 1)* pageList.getPageSize();
		int limitEnd = pageList.getPageSize();
		String sqlCnt = "select count(1) from " + fromTable + " WHERE "+ whereClause;
		String sqlList = "select " + fields + " from " + fromTable + " WHERE "+ whereClause + " order by " + sortBy + " limit " + limitStart+ "," + limitEnd;
		int totalRecord = queryForInt(sqlCnt, objArr);
		int totalPage = 0;
		pageList.setTotalRecord(totalRecord);
		if (totalRecord <= 0)
			return new ArrayList<Map<String, Object>>();
		if (totalRecord > 0)
			totalPage = (totalRecord + pageList.getPageSize() - 1)/ pageList.getPageSize();
		pageList.setTotalPage(totalPage);
		return queryForList(sqlList, objArr);
	}

	public List<?> getObjList(String sql, Class<?> className, Object[] obj) {

		List<?> array = null;
		try {
			array = jdbcTemplate.queryForList(sql,BeanPropertyRowMapper.newInstance(className),obj);
		} catch (Exception e) {
			log.info(e);
		}
		return array;
	}

	public Map<String, ?> getMap(String sql, Object[] obj) {
		Map<String, ?> map = null;
		try {
			map = jdbcTemplate.queryForMap(sql, obj);
		} catch (Exception e) {
			log.info(e);
		}
		return map;
	}

	public Object getObject(String sql,Class<? extends Serializable> className, Object[] obj) {

		Object object = null;
		try {
			object = jdbcTemplate.queryForObject(sql,
					BeanPropertyRowMapper.newInstance(className), obj);
		} catch (EmptyResultDataAccessException e) {
			log.info(e);
		} catch (DataAccessException e) {
			log.info(e);
		}
		return object;
	}
}

class PageList {
	private int currentPage = 1;
	private int pageSize = 15;
	private int totalPage = 0;
	private int totalRecord = 0;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
