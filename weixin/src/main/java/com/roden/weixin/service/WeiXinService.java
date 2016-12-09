package com.roden.weixin.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 核心服务接口
 * 
 * @author luodeng
 * @date 2015-11-02
 */
public interface WeiXinService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request);


}