package com.topia.myapp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.topia.myapp.Util.TimeBeforeUtil;
import com.topia.myapp.controller.PostController;
import com.topia.myapp.dao.FollowDAO;
import com.topia.myapp.dao.PostDAO;
import com.topia.myapp.entity.Post;
import com.topia.myapp.entity.PostList;

@Service
public class PostService {
	
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	private PostDAO pdao;
	
	@Autowired
	private FollowDAO fdao;

	public PostList getPost(int postId) {
		logger.info("================게시글 상세페이지 Service================");
		
		PostList post = new PostList();
		
		int re = pdao.increaseHit(postId);
		
		if(re == 1) {
			post = pdao.getPost(postId);
		}
		
		logger.info(post.toString());
		
		return post;
	}
	
	public int delete(int[] checked) {
		logger.info("================게시글 삭제 Service================");
		int re = -1;
		for(int memId : checked) {
			pdao.delete(memId);
		}
		re = 1;
		return re;
	}
	
	
	public int updatePost(Post post) {
		logger.info("================게시물 수정 Service================");
		
		String url = post.getPostUrl().trim();
		
		if(url.indexOf("/") > 0 && url.split("/").length >3) {
			String[] urlArr = url.split("/");
			String urlId = urlArr[3];
			post.setUrlId(urlId);
		}
		
		logger.info(post.toString());
		
		return pdao.updatePost(post);
	}
	
	public int insertPost(Post post) {
		logger.info("================게시물 등록 Service================");
		
		String url = post.getPostUrl().trim();
		
		if(url.indexOf("/") > 0 && url.split("/").length >3) {
			String[] urlArr = url.split("/");
			String urlId = urlArr[3];
			post.setUrlId(urlId);
		}
		
		logger.info(post.toString());
		
		return pdao.insertPost(post);
	}

	public int postsCnt(String keyword, String searchType) {
		logger.info("================홈화면 글 갯수 Service================");
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("searchType", searchType);
		
		return pdao.postsCnt(param);
	}
	
	public HashMap<String, Object> postsList(int perPage, int page, String keyword, String searchType, int pageSize, String criteria) {
		logger.info("================게시물 리스트,갯수 Service================");
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("searchType", searchType);
		
		int totalCnt = pdao.postsCnt(param);
		
		int totalPages = (totalCnt-1) / perPage + 1;

//		if(totalCnt % perPage == 0) {
//			totalPages = totalCnt / perPage;
//		}

		//		if(totalCnt == perPage) {
		//			totalPages = 0;
		//		}

		if(page > totalPages) {
			page = totalPages;
		}
		int start = perPage * (page - 1) + 1;
		int end = start + perPage - 1;
		if(totalCnt < end) {
			end = totalCnt;
		}
		int startPage = pageSize * ((page-1) / pageSize) + 1;
//		if(page == pageSize) {
//			startPage = 1;
//		}
		int endPage = startPage + pageSize - 1;
		if(totalPages < endPage) {
			endPage = totalPages;
		}
		boolean prev = startPage != 1;
		boolean next = endPage < totalPages;
		
		HashMap<String, Object> pagination = new HashMap<String, Object>();
		pagination.put("page", page);
		pagination.put("perPage", perPage);
		pagination.put("pageSize", pageSize);
		pagination.put("keyword", keyword);
		pagination.put("searchType", searchType);
		pagination.put("criteria", criteria);
		pagination.put("totalCnt", totalCnt);
		pagination.put("totalPages", totalPages);
		pagination.put("startPage", startPage);
		pagination.put("endPage", endPage);
		pagination.put("prev", prev);
		pagination.put("next", next);
		
		HashMap<String, Object> scrollParams = new HashMap<String, Object>();
		scrollParams.put("start", start);
		scrollParams.put("end", end);
		scrollParams.put("criteria", criteria);
		scrollParams.put("keyword", keyword);
		scrollParams.put("searchType", searchType);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		TimeBeforeUtil tu = new TimeBeforeUtil();
		
		List<PostList> list = pdao.postsList(scrollParams);
		
		for(PostList post : list) {
			post.setRegistDateStr(tu.calculateTime(post.getRegistDate()));
		}
		
		resultMap.put("list", list);
		resultMap.put("pagination", pagination);
		
		return resultMap;
	}
	
	public HashMap<String, Object> uploadList(int memId, int perPage, int page) {
		logger.info("================회원의 업로드 수,리스트 Service================");
		
		int totalCnt = pdao.uploadCnt(memId);
		int totalPages = totalCnt/perPage;
		int start = perPage*(page-1)+1;
		int end = start+perPage-1;
		if(end>totalCnt) {
			end = totalCnt;
		}
		
		HashMap<String, Object> scrollParams = new HashMap<String, Object>();
		scrollParams.put("start", start);
		scrollParams.put("end", end);
		scrollParams.put("memId", memId);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cnt", totalCnt);
		resultMap.put("list", pdao.uploadList(scrollParams));
		
		return resultMap;
	}
	
	public int uploadCnt(int memId) {
		logger.info("================업로드영상 갯수 Service================");
		return pdao.uploadCnt(memId);
	}
	
	
	public HashMap<String, Object> recommendsList(int postId, int perPage, int page) {
		logger.info("================추천영상 리스트,갯수 Service================");
		
		int totalCnt = pdao.recommendsCnt(postId);
		int totalPages = totalCnt/perPage;
		int start = perPage*(page-1)+1;
		int end = start+perPage-1;
		if(end>totalCnt) {
			end = totalCnt;
		}
		
		HashMap<String, Object> scrollParams = new HashMap<String, Object>();
		scrollParams.put("start", start);
		scrollParams.put("end", end);
		scrollParams.put("postId", postId);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("cnt", totalCnt);
		List<PostList> list = pdao.recommendsList(scrollParams);
		Collections.shuffle(list); //랜덤하게 정렬
		resultMap.put("list", list);
		
		return resultMap;
	}
	
	public int recommendsCnt(int postId) {
		logger.info("================추천영상 갯수 Service================");
		return pdao.recommendsCnt(postId);
	}


	}
