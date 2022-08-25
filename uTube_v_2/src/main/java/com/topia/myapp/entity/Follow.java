package com.topia.myapp.entity;

import java.util.Date;

public class Follow {
	
	private int followId;
	private int follower;
	private int followed;
	private Date followDate;
	
	@Override
	public String toString() {
		return "Follow [followId=" + followId + ", follower=" + follower + ", followed=" + followed + ", followDate="
				+ followDate + "]";
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
	public Follow(int followId, int follower, int followed, Date followDate) {
		super();
		this.followId = followId;
		this.follower = follower;
		this.followed = followed;
		this.followDate = followDate;
	}
	public Follow() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
