package com.roden.weixin.util;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachePool {
	private static CachePool instance;
	private static Map<String, Object> cacheItems;
	private CachePool() {
		cacheItems = new ConcurrentHashMap<String, Object>();
	}
	/**
	 * 得到唯一实例
	 * 
	 * @return CachePool
	 */
	public static CachePool getInstance() {
		if (instance == null) {
			instance = new CachePool();
		}
		return instance;
	}

	/**
	 * 清除所有Item缓存
	 */
	public void clearAllItems() {
		cacheItems.clear();
	}

	/**
	 * 获取缓存实体
	 * 
	 * @param name
	 * @return
	 */
	public Object getCacheItem(String name) {
		if (!cacheItems.containsKey(name)) {
			return null;
		}
		CacheItem cacheItem = (CacheItem) cacheItems.get(name);
		if (cacheItem.isExpired()) {
			return null;
		}
		return cacheItem.getEntity();
	}

	/**
	 * 存放缓存信息
	 * 
	 * @param name
	 * @param obj
	 * @param expires
	 */
	public void putCacheItem(String name, Object obj, long expires) {
		if (!cacheItems.containsKey(name)) {
			cacheItems.put(name, new CacheItem(obj,expires));
		}
		CacheItem cacheItem = (CacheItem) cacheItems.get(name);
		cacheItem.setCreateTime(new Date());
		cacheItem.setEntity(obj);
		cacheItem.setExpireTime(expires);
	}

	public void putCacheItem(String name, Object obj) {
		putCacheItem(name, obj, -1);
	}

	/**
	 * 移除缓存数据
	 * 
	 * @param name
	 */
	public void removeCacheItem(String name) {
		if (!cacheItems.containsKey(name)) {
			return;
		}
		cacheItems.remove(name);
	}

	/**
	 * 获取缓存数据的数量
	 * 
	 * @return
	 */
	public int getSize() {
		return cacheItems.size();
	}
}

class CacheItem {
	private Date createTime;// 创建缓存的时间
	private long expireTime;//缓存期满的时间
	private Object entity;// 缓存的实体

	public CacheItem(Object obj, long expires) {
		this.entity = obj;
		this.expireTime = expires;
	}   
	public boolean isExpired() {
		return (expireTime != -1 && new Date().getTime() - createTime.getTime() > expireTime);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
