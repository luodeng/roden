package com.roden.weixin.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WeiXinConstant {
	private String appId;
	private String appSecret;
	private String token;	
	public WeiXinConstant() {		
		
	}
	public WeiXinConstant(String name) {		
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("E:/WeiXinConstant.txt");
			properties.load(fileInputStream);
			appId = properties.getProperty(name+"_AppID","");
			appSecret = properties.getProperty(name+"_AppSecret","");
			token = properties.getProperty(name+"_token","");			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	
}
