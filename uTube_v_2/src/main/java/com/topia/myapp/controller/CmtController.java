package com.topia.myapp.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topia.myapp.Util.ClientUtil;
import com.topia.myapp.entity.Cmt;
import com.topia.myapp.entity.CmtList;
import com.topia.myapp.service.CmtService;

@Controller
@RequestMapping("/cmt")
public class CmtController {
	
	@Autowired
	private CmtService cmtService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CmtController.class);
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(int cmtId) {
		
		logger.info("================댓글 삭제 Controller================");
		
		return cmtService.deleteCmt(cmtId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public CmtList detail(int cmtId) {
		
		logger.info("================댓글 상세정보 Controller================");
		
		return cmtService.getCmt(cmtId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
	public int updateSubmit(Cmt cmt, HttpServletRequest request) {
		
		logger.info("================댓글 수정 Controller================");
		
		String ip = ClientUtil.etRemoteAddr(request);
		
		cmt.setUpdateIp(ip);
		
		int re = cmtService.updateCmt(cmt);
		
		if(re==1) {
			logger.info("================댓글 수정 성공================");
		}else {
			logger.info("================댓글 수정 실패================");
		}
		
		return re;
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
	public int insertSubmit(Cmt cmt, HttpServletRequest request) {
		
		logger.info("================댓글 등록 Controller================");
		
		String ip = ClientUtil.etRemoteAddr(request);
		
		cmt.setRegistIp(ip);
		
		logger.info(cmt.toString());
		
		int re = cmtService.insertCmt(cmt);
		
		if(re==1) {
			logger.info("================댓글 등록 성공================");
		}else {
			logger.info("================댓글 등록 실패================");
		}

		return re;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public HashMap<String, Object> list(int postId, @RequestParam(defaultValue = "10") int perPage, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
		
		logger.info("================댓글 리스트 출력 Controller================");
		
		return cmtService.listCmt(postId, perPage, page, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCmtReply", method = RequestMethod.GET)
	public HashMap<String, Object> listCmtReply(int cmtRef) {
		
		logger.info("================대댓글 리스트 출력 Controller================");
		
		return cmtService.listCmtReply(cmtRef);
	}
	
	
}
