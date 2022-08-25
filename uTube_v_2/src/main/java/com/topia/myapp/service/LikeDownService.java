package com.topia.myapp.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.myapp.HomeController;
import com.topia.myapp.dao.LikeDownDAO;

@Service
public class LikeDownService {
	
	@Autowired
	private LikeDownDAO ldao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public int hate(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.hate(param);
	}

	public int hateCnt(int postId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		return ldao.hateCnt(param);
	}

	public int checkHate(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.checkHate(param);
	}

	public int hateCancel(int postId, int memId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("postId", postId);
		param.put("memId", memId);
		return ldao.hateCancel(param);
	}
	
	
	
	
}
