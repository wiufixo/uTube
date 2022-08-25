package com.topia.myapp.entity;

import java.util.Date;

public class Member {

	private int memId;
	private String memName;
	private String password;
	private String auth;
	private Date registDate;
	private String registDateStr;
	private Date updateDate;
	private int registId;
	private int updateId;
	private String registIp;
	private String updateIp;
	private String email;
	private String image;
	private String saveImage;
	private String channelName;
	private int followers;
	private int followings;
	private int uploads;
	@Override
	public String toString() {
		return "Member [memId=" + memId + ", memName=" + memName + ", password=" + password + ", auth=" + auth
				+ ", registDate=" + registDate + ", registDateStr=" + registDateStr + ", updateDate=" + updateDate
				+ ", registId=" + registId + ", updateId=" + updateId + ", registIp=" + registIp + ", updateIp="
				+ updateIp + ", email=" + email + ", image=" + image + ", saveImage=" + saveImage + ", channelName="
				+ channelName + ", followers=" + followers + ", followings=" + followings + ", uploads=" + uploads
				+ "]";
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
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
	public int getRegistId() {
		return registId;
	}
	public void setRegistId(int registId) {
		this.registId = registId;
	}
	public int getUpdateId() {
		return updateId;
	}
	public void setUpdateId(int updateId) {
		this.updateId = updateId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getFollowings() {
		return followings;
	}
	public void setFollowings(int followings) {
		this.followings = followings;
	}
	public int getUploads() {
		return uploads;
	}
	public void setUploads(int uploads) {
		this.uploads = uploads;
	}
	public Member(int memId, String memName, String password, String auth, Date registDate, String registDateStr,
			Date updateDate, int registId, int updateId, String registIp, String updateIp, String email, String image,
			String saveImage, String channelName, int followers, int followings, int uploads) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.password = password;
		this.auth = auth;
		this.registDate = registDate;
		this.registDateStr = registDateStr;
		this.updateDate = updateDate;
		this.registId = registId;
		this.updateId = updateId;
		this.registIp = registIp;
		this.updateIp = updateIp;
		this.email = email;
		this.image = image;
		this.saveImage = saveImage;
		this.channelName = channelName;
		this.followers = followers;
		this.followings = followings;
		this.uploads = uploads;
	}
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
}
