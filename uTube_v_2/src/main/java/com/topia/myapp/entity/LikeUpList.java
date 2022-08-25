package com.topia.myapp.entity;

public class LikeUpList {
	
	private int likeId;
	private int postId;
	private int memId;
	
	@Override
	public String toString() {
		return "LikeUp [likeId=" + likeId + ", postId=" + postId + ", memId=" + memId + "]";
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
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
	public LikeUpList(int likeId, int postId, int memId) {
		super();
		this.likeId = likeId;
		this.postId = postId;
		this.memId = memId;
	}
	public LikeUpList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
