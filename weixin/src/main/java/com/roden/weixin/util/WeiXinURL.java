package com.roden.weixin.util;

public class WeiXinURL {
	public static final String MCH_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";	
	public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String CODE_ACCESSTOKEN_URL= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String 	TEMPLATE_URL= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=TOKEN";
	public static final String OAUTH_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	public static final String USER_INFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";	
	public static final String GETTICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
}
