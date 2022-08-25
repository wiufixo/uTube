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
import com.topia.myapp.entity.FollowList;
import com.topia.myapp.entity.Post;
import com.topia.myapp.entity.PostList;

@Repository
public class FollowDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FollowDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
//	public PostList getPost(int postId) {
//		return sqlSessionTemplate.selectOne("post.getPost", postId);
//	}
	
	public List<FollowList> followingList(HashMap<String, Object> param) {
		logger.info("================팔로잉 리스트 DAO================");
		return sqlSessionTemplate.selectList("follow.followingList", param);
	}
	
	public int followingCnt(HashMap<String, Object> param) {
		logger.info("================팔로잉 수 DAO================");
		return sqlSessionTemplate.selectOne("follow.followingCnt", param);
	}
	
	public List<FollowList> followerList(HashMap<String, Object> param) {
		logger.info("================팔로워 리스트 DAO================");
		return sqlSessionTemplate.selectList("follow.followerList", param);
	}
	
	public int followerCnt(HashMap<String, Object> param) {
		logger.info("================팔로워 수 DAO================");
		return sqlSessionTemplate.selectOne("follow.followerCnt", param);
	}
	
	public int checkFollow(HashMap<String, Object> param) {
		logger.info("================팔로잉 여부 DAO================");
		return sqlSessionTemplate.selectOne("follow.checkFollow", param);
	}

	public int follow(HashMap<String, Object> param) {
		return sqlSessionTemplate.insert("follow.follow", param);
	}

	public int followCancel(HashMap<String, Object> param) {
		return sqlSessionTemplate.delete("follow.followCancel", param);
	}

	
//	public List<PostList> recommendsList(HashMap<String, Object> scrollParams) {
//		logger.info("================추천영상 리스트 DAO================");
//		return sqlSessionTemplate.selectList("post.recommendsList", scrollParams);
//	}
//	
//	public int recommendsCnt(int postId) {
//		logger.info("================추천영상 갯수 DAO================");
//		HashMap<String, Object> param = new HashMap<String, Object>();
//		param.put("postId", postId);
//		return sqlSessionTemplate.selectOne("post.recommendsCnt", param);
//	}
//	
//	public int delete(int postId) {
//		logger.info("================게시물 삭제 DAO================");
//		return sqlSessionTemplate.delete("post.delete", postId);
//	}
//		
//	public int increaseHit(int postId) {
//		logger.info("================게시물 조회수 증가 DAO================");
//		return sqlSessionTemplate.update("post.increaseHit", postId);
//	}
//	
//	public int updatePost(Post post) {
//		logger.info("================게시물 수정 DAO================");
//		logger.info(post.toString());
//		return sqlSessionTemplate.update("post.updatePost", post);
//	}
//	
//	public int insertPost(Post post) {
//		logger.info("================게시물 등록 DAO================");
//		logger.info(post.toString());
//		return sqlSessionTemplate.insert("post.insert", post);
//	}

}
