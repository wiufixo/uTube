package com.topia.myapp.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.myapp.HomeController;

@Repository
public class LikeDownDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public int hate(HashMap<String, Object> param) {
		return sqlSessionTemplate.insert("hate.hate", param);
	}

	public int hateCnt(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("hate.hateCnt", param);
	}

	public int checkHate(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("hate.checkHate", param);
	}

	public int hateCancel(HashMap<String, Object> param) {
		return sqlSessionTemplate.delete("hate.hateCancel", param);
	}
}
