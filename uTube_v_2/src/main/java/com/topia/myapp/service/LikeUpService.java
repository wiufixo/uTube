package com.topia.myapp.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.myapp.HomeController;
import com.topia.myapp.dao.LikeUpDAO;

@Service
public class LikeUpService {
	
	@Autowired
	private LikeUpDAO ldao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public int like(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.like(param);
	}

	public int likeCnt(int postId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		return ldao.likeCnt(param);
	}

	public int checkLike(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.checkLike(param);
	}

	public int likeCancel(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.likeCancel(param);
	}
	
	
	
	
}
