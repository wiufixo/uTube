package com.topia.myapp.entity;

import java.util.Date;

public class Cmt {
	
	private int cmtId;
	private String cmtContent;
	private int postId;
	private int memId;
	private Date registDate;
	private Date updateDate;
	private String registIp;
	private String updateIp;
	private int cmtRef;
	private int cmtStep;
	private int cmtLevel;
	private String refMemName;
	@Override
	public String toString() {
		return "Cmt [cmtId=" + cmtId + ", cmtContent=" + cmtContent + ", postId=" + postId + ", memId=" + memId
				+ ", registDate=" + registDate + ", updateDate=" + updateDate + ", registIp=" + registIp + ", updateIp="
				+ updateIp + ", cmtRef=" + cmtRef + ", cmtStep=" + cmtStep + ", cmtLevel=" + cmtLevel + ", refMemName="
				+ refMemName + "]";
	}
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
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
	public int getCmtRef() {
		return cmtRef;
	}
	public void setCmtRef(int cmtRef) {
		this.cmtRef = cmtRef;
	}
	public int getCmtStep() {
		return cmtStep;
	}
	public void setCmtStep(int cmtStep) {
		this.cmtStep = cmtStep;
	}
	public int getCmtLevel() {
		return cmtLevel;
	}
	public void setCmtLevel(int cmtLevel) {
		this.cmtLevel = cmtLevel;
	}
	public String getRefMemName() {
		return refMemName;
	}
	public void setRefMemName(String refMemName) {
		this.refMemName = refMemName;
	}
	public Cmt(int cmtId, String cmtContent, int postId, int memId, Date registDate, Date updateDate, String registIp,
			String updateIp, int cmtRef, int cmtStep, int cmtLevel, String refMemName) {
		super();
		this.cmtId = cmtId;
		this.cmtContent = cmtContent;
		this.postId = postId;
		this.memId = memId;
		this.registDate = registDate;
		this.updateDate = updateDate;
		this.registIp = registIp;
		this.updateIp = updateIp;
		this.cmtRef = cmtRef;
		this.cmtStep = cmtStep;
		this.cmtLevel = cmtLevel;
		this.refMemName = refMemName;
	}
	public Cmt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
