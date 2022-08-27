package com.topia.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.topia.myapp.Util.ClientUtil;
import com.topia.myapp.Util.FIleUtil;
import com.topia.myapp.entity.Member;
import com.topia.myapp.service.FollowService;
import com.topia.myapp.service.MemberService;
import com.topia.myapp.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private PostService postService;

	@Autowired
	private FIleUtil fu;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mav, @RequestParam(defaultValue = "0") int memId) {
		logger.info("**********유저 상세 페이지로 이동 Controller**********");
		
		Member member = memberService.findByMemId(memId);
		
		if(member==null || memId==0) {
			mav.setViewName("error/detailError");
		}else {
			mav.addObject("member", member);
			mav.addObject("followerList", followService.followerList(memId)); //구독자리스트
			mav.addObject("followingList", followService.followingList(memId)); //구독리스트
			mav.addObject("uploadCnt", postService.uploadCnt(memId)); //업로드리스트
			mav.setViewName("member/detail");
		}
		return mav;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session, ModelAndView mav, @RequestParam(defaultValue = "5") int perPage, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "mem_id") String criteria, @RequestParam(defaultValue = "5") int pageSize) {
		logger.info("**********유저 목록 페이지로 이동 Controller**********");
		
		Member login = (Member) session.getAttribute("login");
		
		if(login==null || login.getAuth().equals("USER")) {
			mav.setViewName("error/accessError");
		}else {
			mav.setViewName("member/list");
			mav.addObject("re", memberService.membersList(perPage, page, keyword, searchType, criteria, pageSize));
		}

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ArrayList<Member> userList() {
		logger.info("**********유저 목록 Controller**********");
		
		return memberService.userList();
	}

	@RequestMapping(value = "find", method = RequestMethod.GET)
	public ModelAndView find(HttpSession session, ModelAndView mav) {
		
		logger.info("**********찾기 페이지로 이동 Controller**********");
		
		Member login = (Member) session.getAttribute("login");
		
		if(login != null) {
			mav.setViewName("error/accessError");
		}else {
			mav.setViewName("member/find");
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "findPassword", method = RequestMethod.POST)
	public String findPassword(String memName, String email) {
		
		logger.info("**********아이디찾기 Controller**********");
		
		return memberService.findPassword(memName, email);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session, ModelAndView mav) {
		
		logger.info("**********로그인 페이지로 이동 Controller**********");
		
		Member login = (Member) session.getAttribute("login");
		
		if(login != null) {
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("member/login");
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public int loginSubmit(Member member, HttpSession session) {
		logger.info("**********로그인 처리 Controller**********");
		logger.info(member.toString());

		Member login = memberService.login(member);
		int re = 0;

		if(login == null) {
			logger.info("**********로그인 실패**********");
			re = -1;

		}else {
			logger.info("**********로그인 성공**********");
			session.setAttribute("login", login);
			re = 1;
		}

		return re;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("**********로그아웃 Controller**********");

		session.removeAttribute("login");

		return "redirect:/";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView JoinForm(ModelAndView mav, HttpSession session) {
		
		mav.setViewName("member/join");
		
		Member login = (Member) session.getAttribute("login");
		
		if(login == null) {
			mav.addObject("title", "회원가입");
			mav.addObject("button", "가입");
		}else {
			if(login.getAuth().equals("MANAGER") || login.getAuth().equals("MASTER")) {
				mav.addObject("title", "회원등록");
				mav.addObject("button", "등록");
			}else if(login.getAuth().equals("USER")){
				mav.setViewName("error/accessError");
			}
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public int idcheck(String memName) {
		int re = -1;
		Member member = memberService.idcheck(memName);
		System.out.println(member);
		if(member != null) {
			re = 1;
		}
		return re;
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinSubmit", method = RequestMethod.POST)
	public int join(Member member, MultipartFile uploadFile, HttpServletRequest request, HttpSession session) {
		logger.info("****************회원가입 Controller****************");
		
		Member login = (Member) session.getAttribute("login");
		if(login != null) {
			member.setRegistId(login.getMemId());
		}
		
		String ip = ClientUtil.etRemoteAddr(request);
		member.setRegistIp(ip);

		if(uploadFile.getSize() > 1) {
			String originalName = uploadFile.getOriginalFilename();
			member.setImage(originalName);
		}else {
			member.setImage("no_image.png");
		}

		String saveName = fu.uploadFiles(uploadFile);
		member.setSaveImage(saveName);

		logger.info(member.toString());

		int re = memberService.join(member);
		
		if(re==1) {
			logger.info("****************회원가입 성공****************");
		}else {
			logger.info("****************회원가입 실패****************");
		}

//		if(login != null) {
//			return "redirect:./list";
//		}
//		return "redirect:./login";
		return re;
	} 

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam(defaultValue = "0") int memId, HttpSession session, ModelAndView mav) {
		
		Member login = (Member) session.getAttribute("login");
		
		if(login==null || memId==0 || (login.getAuth().equals("USER") && (login.getMemId()!=memId))) {
			mav.setViewName("error/accessError");
		}else{
			mav.setViewName("member/update");
			mav.addObject("member", memberService.findByMemId(memId));
			System.out.println(memberService.findByMemId(memId).toString());
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
	public int update(Member member, MultipartFile uploadFile, HttpServletRequest request, HttpSession session) {
		logger.info("****************회원수정 Controller****************");
		
		Member updateMember = (Member) session.getAttribute("login");
		int updateId = updateMember.getMemId();
		int updatedId = member.getMemId();
		
		Boolean iam = true;
		
		if(updatedId != updateId) { //본인이 아닌 관리자가 유저정보 수정할때
			iam = false;
			member.setUpdateId(updateId);
		}else{
			member.setUpdateId(updatedId);
		};
		
		String ip = ClientUtil.etRemoteAddr(request);
		member.setUpdateIp(ip);
		
//		Member updatedMember = memberService.findByMemId(updatedId);
//		String noImage = "no_image.png";

		if(uploadFile.getSize() > 1) {
			String originalName = uploadFile.getOriginalFilename();
			member.setImage(originalName);
			String saveName = fu.uploadFiles(uploadFile);
			member.setSaveImage(saveName);
		}

		logger.info(member.toString());

		int re = memberService.update(member);

		Member login = memberService.findByMemId(member.getMemId());

		if(re==1) {
			logger.info("****************회원수정 성공****************");
			if(iam) {
				session.setAttribute("login", login);
			}
		}else {
			logger.info("****************회원수정 실패****************");
		}

		return re;
	}  
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(int[] checked) {
		return memberService.delete(checked);
	}
}
