package com.topia.myapp.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.topia.myapp.Util.ClientUtil;
import com.topia.myapp.entity.Member;
import com.topia.myapp.entity.Post;
import com.topia.myapp.entity.PostList;
import com.topia.myapp.service.CmtService;
import com.topia.myapp.service.FollowService;
import com.topia.myapp.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CmtService cmttService;

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session, ModelAndView mav, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "10") int perPage, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "post_id") String criteria) {
		
		logger.info("================게시물 리스트페이지(관리자용)로 Controller================");

		Member login = (Member) session.getAttribute("login");
		
		if(login==null || login.getAuth().equals("USER")) {
			mav.setViewName("error/accessError");
		}else {
			mav.setViewName("post/list");
			mav.addObject("re", postService.postsList(perPage, page, keyword, searchType, pageSize, criteria));
		}

		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/postsList", method = RequestMethod.GET)
	public HashMap<String, Object> postsList (int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "post_id") String criteria) {
		
		logger.info("================홈화면 게시글 리스트 RestController================");
		
		return postService.postsList(8, page, keyword, searchType, pageSize, criteria);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberPosts", method = RequestMethod.GET)
	public HashMap<String, Object> memberPosts (int page, int memId, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "post_id") String criteria) {
		
		logger.info("================회원의 게시글 리스트 RestController================");
		
		return postService.uploadList(memId, 8, page);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommends", method = RequestMethod.GET)
	public HashMap<String, Object> recommends (int postId, int page) {
		
		logger.info("================추천영상 리스트 RestController================");
		
		return postService.recommendsList(postId, 10, page);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpSession session, int postId, ModelAndView mav) {
		
		logger.info("================게시물 상세페이지로 Controller================");
		
		//Member login = (Member) session.getAttribute("login");

		PostList post = postService.getPost(postId);
		mav.setViewName("error/detailError");
		
		if(post.getPostId() != 0) {
			mav.addObject("post", post);
			mav.addObject("cmtsCnt", cmttService.listCnt(postId)); //댓글총갯수
			mav.addObject("recommendsCnt", postService.recommendsCnt(postId)); //추천영상갯수
			mav.setViewName("post/detail");
		}
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deletePost(int[] checked) {
		
		logger.info("================게시물 삭제 Controller================");
		
		int re =postService.delete(checked);
		
		return re;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updatePost(@RequestParam(defaultValue = "0") int postId, ModelAndView mav, HttpSession session) {
		
		logger.info("================게시물 수정페이지로 Controller================");

		Member login = (Member) session.getAttribute("login");
		PostList post = postService.getPost(postId);
		
		if(login==null || postId==0 || (login.getAuth().equals("USER") && (login.getMemId()!=post.getMemId()))) {
			mav.setViewName("error/accessError");
		}else{
			mav.setViewName("post/update");
			mav.addObject("post", post);
			System.out.println(post.toString());
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
	public ModelAndView updatePostSubmit(Post post, HttpServletRequest request, HttpSession session, ModelAndView mav) {
		
		logger.info("================게시물 수정 Controller================");
		
		Member login = (Member) session.getAttribute("login");
		
		String ip = ClientUtil.etRemoteAddr(request);
		logger.info(ip);
		
		post.setUpdateIp(ip);
		
		logger.info(post.toString());
		
		int re = postService.updatePost(post);
		
		if(re==1) {
			logger.info("================게시물 수정 성공================");
			mav.addObject("post", postService.getPost(post.getPostId()));
			if(login.getMemId() == post.getMemId()) {
				mav.setViewName("redirect:./detail?postId="+post.getPostId());
			}else {
				mav.setViewName("redirect:./list");
			}
		}else {
			logger.info("================게시물 수정 실패================");
			mav.setViewName("post/home");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insertPost(ModelAndView mav, HttpSession session) {
		
		logger.info("================게시물 작성페이지로 Controller================");

		Member login = (Member) session.getAttribute("login");
		
		if(login == null) {
			mav.setViewName("error/accessError");
		}else{
			mav.setViewName("post/insert");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
	public String insertPostSubmit(Post post, HttpServletRequest request) {
		
		logger.info("================게시물 등록 Controller================");
		
		String ip = ClientUtil.etRemoteAddr(request);
		logger.info(ip);
		
		post.setRegistIp(ip);
		
		logger.info(post.toString());
		
		int re = postService.insertPost(post);
		if(re==1) {
			logger.info("================게시물 등록 성공================");
		}else {
			logger.info("================게시물 등록 실패================");
		}
		
		return "redirect:/";
	}
}
