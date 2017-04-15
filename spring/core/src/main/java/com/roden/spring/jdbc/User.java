package com.roden.spring.jdbc;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public User() {
		initMetaData();
	}
	protected void initMetaData() {
		Class<?> clz = this.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.getGenericType().toString().equals("class java.lang.String"))
					field.set(this, "");
				if (field.getGenericType().toString().equals("class java.lang.Integer"))
					field.set(this, 0);
				if (field.getGenericType().toString().equals("class java.lang.Double"))
					field.set(this, 0d);
				if (field.getGenericType().toString().equals("class java.math.BigDecimal"))
					field.set(this, new java.math.BigDecimal(0));
				if (field.getGenericType().toString().equals("class java.util.Date"))
					field.set(this, new Date());
				if (field.getGenericType().toString().equals("class java.lang.Short"))
					field.set(this, 0f);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private Integer id;
	private String mobile;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
