package com.topia.myapp.entity;

import java.util.Date;

public class Post {

	private int postId;
	private String postTitle;
	private String postUrl;
	private String postContent;
	private Date registDate;
	private Date updateDate;
	private String registIp;
	private String updateIp;
	private int memId;
	private int hit;
	private String urlId;
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postTitle=" + postTitle + ", postUrl=" + postUrl + ", postContent="
				+ postContent + ", registDate=" + registDate + ", updateDate=" + updateDate + ", registIp=" + registIp
				+ ", updateIp=" + updateIp + ", memId=" + memId + ", hit=" + hit + ", urlId=" + urlId + "]";
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

	public Post(int postId, String postTitle, String postUrl, String postContent, Date registDate, Date updateDate,
			String registIp, String updateIp, int memId, int hit, String urlId) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postUrl = postUrl;
		this.postContent = postContent;
		this.registDate = registDate;
		this.updateDate = updateDate;
		this.registIp = registIp;
		this.updateIp = updateIp;
		this.memId = memId;
		this.hit = hit;
		this.urlId = urlId;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
