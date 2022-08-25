package com.topia.myapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topia.myapp.entity.Cmt;
import com.topia.myapp.entity.CmtList;

@Repository
public class CmtDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CmtDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public CmtList getCmt(int cmtId) {
		return sqlSessionTemplate.selectOne("cmt.getCmt", cmtId);
	}
	
	public List<CmtList> listCmt(HashMap<String, Object> scrollParams) {
		logger.info("================댓글 리스트 DAO================");
		return sqlSessionTemplate.selectList("cmt.listCmt", scrollParams);
	}
	
	public int listCnt(int postId) {
		logger.info("================댓글 갯수 DAO================");
		return sqlSessionTemplate.selectOne("cmt.listCnt", postId);
	}
	
	public int deleteCmt(int cmtId) {
		logger.info("================댓글 삭제 DAO================");
		return sqlSessionTemplate.delete("cmt.deleteCmt", cmtId);
	}
		
	public int updateCmt(Cmt cmt) {
		logger.info("================댓글 수정 DAO================");
		logger.info(cmt.toString());
		return sqlSessionTemplate.update("cmt.updateCmt", cmt);
	}
	
	public int insertCmt(Cmt cmt) {
		logger.info("================댓글 등록 DAO================");
		logger.info(cmt.toString());
		return sqlSessionTemplate.insert("cmt.insert", cmt);
	}

	public int plusStep(int cmtRef, int cmtStep) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cmtRef", cmtRef);
		map.put("cmtStep", cmtStep);
		return sqlSessionTemplate.update("cmt.plusStep", map);
	}
	public int getMaxStep(int cmtRef) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cmtRef", cmtRef);
		return sqlSessionTemplate.selectOne("cmt.getMaxStep", map);
	}

	public int checkStep(int cmtRef, int cmtStep, int cmtLevel) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cmtRef", cmtRef);
		map.put("cmtStep", cmtStep);
		map.put("cmtLevel", cmtLevel);
		return sqlSessionTemplate.selectOne("cmt.checkStep", map);
	}

	public List<CmtList> listCmtReply(int cmtRef) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cmtRef", cmtRef);
		return sqlSessionTemplate.selectList("cmt.listCmtReply", map);
	}

}
