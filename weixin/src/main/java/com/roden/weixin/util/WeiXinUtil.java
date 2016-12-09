package com.roden.weixin.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roden.weixin.entity.AccessToken;

public class WeiXinUtil {
	public static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	public static final String CLW="clw";
	public static final String SHOP="shop";
	public static final String Keeper="keeper";
	public static final String LuoDeng="luodeng";
	public static String getToken(String name) throws IOException, Exception{
		Object token=CachePool.getInstance().getCacheItem(name+"_token");
		if(token!=null){
			return (String)token;
		}else{	
			WeiXinConstant WeiXinConstant=getWeiXinConstant(name);
			AccessToken accessToken=HttpUtil.getAccessToken(WeiXinConstant.getAppId(), WeiXinConstant.getAppSecret()); 
			CachePool.getInstance().putCacheItem(name+"_token", accessToken.getAccessToken(), 1000*60*60);
			return accessToken.getAccessToken();
		}
	}
	public static WeiXinConstant getWeiXinConstant(String name){
		return new WeiXinConstant(name);
	}
	//此token为获取用户授权信息，注意与基础token区分使用
	public static String getCodeAccessToken(String code,String name){
		WeiXinConstant weiXinConstant=getWeiXinConstant(name);
		String requestUrl = WeiXinURL.CODE_ACCESSTOKEN_URL.replace("APPID" , weiXinConstant.getAppId())
				.replace("SECRET" , weiXinConstant.getAppSecret())
				.replace("CODE", code);
		String json = HttpUtil.httpRequest(requestUrl , "GET" , null);
		JSONObject jsonObject =JSON.parseObject(json);		
		// 如果请求成功
		if (null != jsonObject) {
			try {			
				return jsonObject.getString("openid");				
			} catch (Exception e) {				
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return "";
	}
	
}

