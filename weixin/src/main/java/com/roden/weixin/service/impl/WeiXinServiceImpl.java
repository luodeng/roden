package com.roden.weixin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.roden.weixin.entity.message.Article;
import com.roden.weixin.entity.message.NewsMessage;
import com.roden.weixin.entity.message.TextMessage;
import com.roden.weixin.service.WeiXinService;
import com.roden.weixin.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author luodeng
 * @date 2015-11-02
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {
	
	private static Logger log = LoggerFactory.getLogger(WeiXinServiceImpl.class);
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			System.out.println("");
			System.out.println("msgType:"+msgType);
			System.out.println("fromUserName:"+fromUserName);
			System.out.println("toUserName:"+toUserName);

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义			
			StringBuffer contentMsg = new StringBuffer();  
			contentMsg.append("欢迎来到微信公众开发平台").append("\n");  			
			contentMsg.append("点击查看 <a href=\"http://weibo.com/luodengblog/home\">个人微博</a>");  

			textMessage.setContent(contentMsg.toString());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
			
			switch(msgType){
			case MessageUtil.REQ_MESSAGE_TYPE_TEXT:
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				System.out.println("content:"+content);
				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				switch(content){
				// 单图文消息
				case "1":{
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					article.setDescription("有问题请私信个人微博");
					article.setPicUrl("http://i3.sinaimg.cn/cj/roll/20151026/1445812519_WG6JQY.jpg");
					article.setUrl("http://weibo.com/luodengblog/home");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					break;
				}				
				// 多图文消息---首条消息不含图片
				case "2":{
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("http://weibo.com/luodengblog/home");
	
					Article article2 = new Article();
					article2.setTitle("微信公众帐号开发");
					article2.setDescription("");
					article2.setPicUrl("http://i3.sinaimg.cn/cj/roll/20151026/1445812519_WG6JQY.jpg");
					article2.setUrl("http://weibo.com/luodengblog/home");
	
					Article article3 = new Article();
					article3.setTitle("微信公众帐号开发");
					article3.setDescription("");
					article3.setPicUrl("http://i3.sinaimg.cn/cj/roll/20151026/1445812519_WG6JQY.jpg");
					article3.setUrl("http://weibo.com/luodengblog/home");
	
					Article article4 = new Article();
					article4.setTitle("微信公众帐号开发");
					article4.setDescription("");
					article4.setPicUrl("http://i3.sinaimg.cn/cj/roll/20151026/1445812519_WG6JQY.jpg");
					article4.setUrl("http://weibo.com/luodengblog/home");
	
					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					break;
				}				
				}					
			case MessageUtil.REQ_MESSAGE_TYPE_EVENT:
				// 接收用户发送的事件请求内容
				String Event = requestMap.get("Event");
				String EventKey = requestMap.get("EventKey");
				System.out.println("EventKey:"+Event);
				System.out.println("EventKey:"+EventKey);
				break;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
}