package com.topia.myapp.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.myapp.dao.FollowDAO;

@Service
public class FollowService {
	
	@Autowired
	private FollowDAO fdao;
	
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	public HashMap<String, Object> followingList(int memId) {
		logger.info("================팔로잉 리스트,갯수 Service================");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("follower", memId);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cnt", fdao.followingCnt(param));
		resultMap.put("list", fdao.followingList(param));
		
		return resultMap;
	}
	public HashMap<String, Object> followerList(int memId) {
		logger.info("================팔로워 리스트,갯수 Service================");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("followed", memId);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cnt", fdao.followerCnt(param));
		resultMap.put("list", fdao.followerList(param));
		
		return resultMap;
	}

	public int followerCnt(int followed) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("followed", followed);
		return fdao.followerCnt(param);
	}
	
	public int checkFollow(int follower, int followed) {
		logger.info("================팔로우 체크 Service================");
		HashMap<String, Object> param = new HashMap<>();
		param.put("follower", follower);
		param.put("followed", followed);
		return fdao.checkFollow(param);
	}

	public int follow(int follower, int followed) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("follower", follower);
		param.put("followed", followed);
		return fdao.follow(param);
	}

	public int followCancel(int follower, int followed) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("follower", follower);
		param.put("followed", followed);
		return fdao.followCancel(param);
	}
}
