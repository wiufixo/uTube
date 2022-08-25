package com.topia.myapp.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.myapp.HomeController;

@Repository
public class LikeUpDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public int like(HashMap<String, Object> param) {
		return sqlSessionTemplate.insert("like.like", param);
	}

	public int likeCnt(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("like.likeCnt", param);
	}

	public int checkLike(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("like.checkLike", param);
	}

	public int likeCancel(HashMap<String, Object> param) {
		return sqlSessionTemplate.delete("like.likeCancel", param);
	}
}
