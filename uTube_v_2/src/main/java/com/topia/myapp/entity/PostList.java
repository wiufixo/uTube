package com.topia.myapp.entity;

import java.util.Date;

public class PostList {
	
	private int likes;
	private int cmts;
	private int postId;
	private String postTitle;
	private String postUrl;
	private String postContent;
	private Date registDate;
	private String registDateStr;
	private Date updateDate;
	private String registIp;
	private String updateIp;
	private int memId;
	private int hit;
	private String urlId;
	private String memName;
	private String password;
	private String auth;
	private String image;
	private String channelName;
	private int followers;
	@Override
	public String toString() {
		return "PostList [likes=" + likes + ", cmts=" + cmts + ", postId=" + postId + ", postTitle=" + postTitle
				+ ", postUrl=" + postUrl + ", postContent=" + postContent + ", registDate=" + registDate
				+ ", registDateStr=" + registDateStr + ", updateDate=" + updateDate + ", registIp=" + registIp
				+ ", updateIp=" + updateIp + ", memId=" + memId + ", hit=" + hit + ", urlId=" + urlId + ", memName="
				+ memName + ", password=" + password + ", auth=" + auth + ", image=" + image + ", channelName="
				+ channelName + ", followers=" + followers + "]";
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getCmts() {
		return cmts;
	}
	public void setCmts(int cmts) {
		this.cmts = cmts;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public String getRegistDateStr() {
		return registDateStr;
	}
	public void setRegistDateStr(String registDateStr) {
		this.registDateStr = registDateStr;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getRegistIp() {
		return registIp;
	}
	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
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
	public PostList(int likes, int cmts, int postId, String postTitle, String postUrl, String postContent,
			Date registDate, String registDateStr, Date updateDate, String registIp, String updateIp, int memId,
			int hit, String urlId, String memName, String password, String auth, String image, String channelName,
			int followers) {
		super();
		this.likes = likes;
		this.cmts = cmts;
		this.postId = postId;
		this.postTitle = postTitle;
		this.postUrl = postUrl;
		this.postContent = postContent;
		this.registDate = registDate;
		this.registDateStr = registDateStr;
		this.updateDate = updateDate;
		this.registIp = registIp;
		this.updateIp = updateIp;
		this.memId = memId;
		this.hit = hit;
		this.urlId = urlId;
		this.memName = memName;
		this.password = password;
		this.auth = auth;
		this.image = image;
		this.channelName = channelName;
		this.followers = followers;
	}
	public PostList() {
		super();
		// TODO Auto-generated constructor stub
	}
}
