package com.topia.myapp.entity;

import java.util.Date;

public class FollowList {
	
	private int followId;
	private int follower;
	private int followed;
	private Date followDate;
	private String memName;
	private String password;
	private String auth;
	private String image;
	private String saveImage;
	private String channelName;
	private int followers;
	
	@Override
	public String toString() {
		return "FollowList [followId=" + followId + ", follower=" + follower + ", followed=" + followed
				+ ", followDate=" + followDate + ", memName=" + memName + ", password=" + password + ", auth=" + auth
				+ ", image=" + image + ", saveImage=" + saveImage + ", channelName=" + channelName + ", followers="
				+ followers + "]";
	}
	public int getFollowId() {
		return followId;
	}
	public void setFollowId(int followId) {
		this.followId = followId;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
	}
	public int getFollowed() {
		return followed;
	}
	public void setFollowed(int followed) {
		this.followed = followed;
	}
	public Date getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSaveImage() {
		return saveImage;
	}
	public void setSaveImage(String saveImage) {
		this.saveImage = saveImage;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public FollowList(int followId, int follower, int followed, Date followDate, String memName, String password,
			String auth, String image, String saveImage, String channelName, int followers) {
		super();
		this.followId = followId;
		this.follower = follower;
		this.followed = followed;
		this.followDate = followDate;
		this.memName = memName;
		this.password = password;
		this.auth = auth;
		this.image = image;
		this.saveImage = saveImage;
		this.channelName = channelName;
		this.followers = followers;
	}
	public FollowList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
