package com.topia.myapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.myapp.controller.PostController;
import com.topia.myapp.entity.Member;
import com.topia.myapp.entity.Post;
import com.topia.myapp.entity.PostList;

@Repository
public class MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Member> membersList(HashMap<String, Object> pageParams) {
		logger.info("================회원 리스트 DAO================");
		return sqlSessionTemplate.selectList("member.membersList", pageParams);
	}
	
	public int membersCnt(HashMap<String, Object> param) {
		logger.info("================회원수 DAO================");
		return sqlSessionTemplate.selectOne("member.membersCnt", param);
	}
	
	
	public int delete(int memId) {
		logger.info("================회원 삭제 DAO================");
		return sqlSessionTemplate.delete("member.delete", memId);
	}
		
	public int join(Member member) {
		logger.info("================회원가입 DAO================");
		logger.info(member.toString());
		return sqlSessionTemplate.insert("member.join", member);
	}
	
	public Member login(Member member) {
		logger.info("*************로그인 DAO*************");
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("memName", member.getMemName());
		param.put("password", member.getPassword());
		return sqlSessionTemplate.selectOne("member.login", param);
	}
	
	public Member findByMemId(int memId) {
		return sqlSessionTemplate.selectOne("member.findByMemId", memId);
	}
	
	public Member findByMemName(String memName) {
		return sqlSessionTemplate.selectOne("member.findByMemName", memName);
	}

	public int update(Member member) {
		return sqlSessionTemplate.update("member.update", member);
	}

	public String findPassword(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("member.findPassword", param);
	}

}
