package com.roden.weixin.entity.message;

/**
 * 文本消息
 * 
 * @author luodeng
 * @date 2015-11-02
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}