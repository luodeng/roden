package com.roden.weixin.entity.message;

/**
 * 音乐消息
 * 
 * @author luodeng
 * @date 2015-11-02
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}