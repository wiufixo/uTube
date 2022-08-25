package com.topia.myapp.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topia.myapp.dao.CmtDAO;
import com.topia.myapp.entity.Cmt;
import com.topia.myapp.entity.CmtList;

@Service
public class CmtService {

	private static final Logger logger = LoggerFactory.getLogger(CmtService.class);

	@Autowired
	private CmtDAO cdao;

	public CmtList getCmt(int cmtId) {
		logger.info("================댓글 한개 정보 Service================");

		return cdao.getCmt(cmtId);
	}
	
	public int listCnt(int postId) {
		logger.info("================게시글의 댓글 갯수 Service================");
		
		return cdao.listCnt(postId);
	}

	public int deleteCmt(int cmtId) {
		logger.info("================댓글 삭제 Service================");

		return cdao.deleteCmt(cmtId);
	}


	public int updateCmt(Cmt cmt) {
		logger.info("================댓글 수정 Service================");

		logger.info(cmt.toString());

		return cdao.updateCmt(cmt);
	}

	public int insertCmt(Cmt cmt) {
		logger.info("================댓글 등록 Service================");

		int oldCmtId = cmt.getCmtId();

		if(oldCmtId != 0) { //대댓글이면
			
			CmtList oldCmt = cdao.getCmt(oldCmtId); //부모댓글 정보
			int oldCmtRef = oldCmt.getCmtRef();
			int oldCmtStep = oldCmt.getCmtStep();
			int oldCmtLevel = oldCmt.getCmtLevel();
			String oldCmtMemName = oldCmt.getMemName();
			
			//cdao.plusStep(oldCmtRef,oldCmtStep);
			
			cmt.setCmtRef(oldCmtRef);
			cmt.setCmtStep(cdao.getMaxStep(oldCmtRef)+1);
			cmt.setCmtLevel(oldCmtLevel+1);
			cmt.setRefMemName(oldCmtMemName);

		}else { //새댓글이면
			cmt.setCmtRef(0);
			cmt.setCmtStep(0);
			cmt.setCmtLevel(0);
		}

		logger.info(cmt.toString());

		return cdao.insertCmt(cmt);
	}

	public HashMap<String, Object> listCmt(int postId, int perPage, int page, int pageSize) {
		logger.info("================댓글 리스트,갯수 Service================");
		
		int totalCnt = cdao.listCnt(postId);
		
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
		
		if(totalCnt == 0) {
			totalPages = 1;
			page = 1;
			start = 1;
			end = 1;
		}

		HashMap<String, Object> pagination = new HashMap<String, Object>();
		pagination.put("page", page);
		pagination.put("perPage", perPage);
		pagination.put("pageSize", pageSize);
		pagination.put("totalCnt", totalCnt);
		pagination.put("totalPages", totalPages);
		pagination.put("startPage", startPage);
		pagination.put("endPage", endPage);
		pagination.put("prev", prev);
		pagination.put("next", next);
		
		HashMap<String, Object> scrollParams = new HashMap<String, Object>();
		scrollParams.put("start", start);
		scrollParams.put("end", end);
		scrollParams.put("postId", postId);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", cdao.listCmt(scrollParams));
		resultMap.put("pagination", pagination);

		return resultMap;
	}

	public HashMap<String, Object> listCmtReply(int cmtRef) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", cdao.listCmtReply(cmtRef));
		return resultMap;
	}
}
