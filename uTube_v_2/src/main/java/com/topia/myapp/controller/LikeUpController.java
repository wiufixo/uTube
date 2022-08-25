package com.topia.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topia.myapp.HomeController;
import com.topia.myapp.service.FollowService;
import com.topia.myapp.service.LikeUpService;


@Controller
@RequestMapping("/like")
public class LikeUpController {
	
	@Autowired
	private LikeUpService likeUpService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insert(int postId, int memId) {
		return likeUpService.like(postId, memId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/likeCnt", method = RequestMethod.GET)
	public int likeCnt(int postId) {
		return likeUpService.likeCnt(postId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkLike", method = RequestMethod.GET)
	public int checkLike(int postId, int memId) { //0 or 1 (count)
		return likeUpService.checkLike(postId, memId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(int postId, int memId) {
		return likeUpService.likeCancel(postId, memId);
	}
	
}
