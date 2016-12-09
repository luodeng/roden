package com.roden.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;



import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.roden.weixin.entity.AccessToken;




public class HttpUtil {	
	public static Logger log = LoggerFactory.getLogger(HttpUtil.class);	
	public static AccessToken getAccessToken(String appid , String appsecret) throws Exception, IOException {
		AccessToken accessToken = null;		
		String requestUrl = WeiXinURL.GET_ACCESSTOKEN_URL.replace("APPID" , appid).replace("APPSECRET" , appsecret);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(requestUrl);
		HttpResponse res = client.execute(get);
		String responseContent = null; // 响应内容
		HttpEntity entity = res.getEntity();
		responseContent = EntityUtils.toString(entity, "UTF-8");
		JSONObject json = JSON.parseObject(responseContent);		
		// 如果请求成功
		if (null != json) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccessToken(json.getString("access_token"));
				accessToken.setExpiresIn(json.getIntValue("expires_in"));
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return accessToken;
	}
	
	/*private static AccessToken getAccessToken2(String appid , String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = WeiXinURL.GET_ACCESSTOKEN_URL.replace("APPID" , appid).replace("APPSECRET" , appsecret);
		String json = httpRequest(requestUrl , "GET" , null);
		JSONObject jsonObject = JSON.parseObject(json);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return accessToken;
	}*/
	
	public static String httpRequest(String requestUrl , String requestMethod , String outputStr) {		
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL" , "SunJSSE");
			sslContext.init(null , tm , new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream , "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("error.");
		}
		return null;
	}
	
}

