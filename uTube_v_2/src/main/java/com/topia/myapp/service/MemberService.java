package com.topia.myapp.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topia.myapp.Util.TimeBeforeUtil;
import com.topia.myapp.dao.MemberDAO;
import com.topia.myapp.entity.Member;
import com.topia.myapp.entity.PostList;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	public HashMap<String, Object> membersList(int perPage, int page, String keyword, String searchType, String criteria, int pageSize) {
		logger.info("================유저 리스트,갯수(관리자용) Service================");
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("searchType", searchType);
		
		int totalCnt = mdao.membersCnt(param);
		
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
		//System.out.println(pagination.toString());
		
		HashMap<String, Object> pageParams = new HashMap<String, Object>();
		pageParams.put("keyword", keyword);
		pageParams.put("searchType", searchType);
		pageParams.put("criteria", criteria);
		pageParams.put("start", start);
		pageParams.put("end", end);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		TimeBeforeUtil tu = new TimeBeforeUtil();
		
		List<Member> list = mdao.membersList(pageParams);
		
		for(Member member : list) {
			member.setRegistDateStr(tu.calculateTime(member.getRegistDate()));
		}
		
		resultMap.put("list", list);
		resultMap.put("pagination", pagination);
		
		return resultMap;
	}
	
	public Member login(Member member) {
		logger.info("****************로그인 Service****************");
		logger.info(member.toString());
		return mdao.login(member);
	}
	
	public int join(Member member) {
		logger.info("****************회원가입 Service****************");
		
		member.setAuth("USER");
		
		logger.info(member.toString());
		return mdao.join(member);
	}
	
	public int update(Member member) {
		return mdao.update(member);
	}

	public Member findByMemId(int memId) {
		return mdao.findByMemId(memId);
	}

	public Member idcheck(String memName) {
		return mdao.findByMemName(memName);
	}

	public int delete(int[] checked) {
		int re = -1;
		for(int postId : checked) {
			mdao.delete(postId);
		}
		re = 1;
		return re;
	}

	public String findPassword(String memName, String email) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("memName", memName);
		param.put("email", email);
		return mdao.findPassword(param);
	}

}
