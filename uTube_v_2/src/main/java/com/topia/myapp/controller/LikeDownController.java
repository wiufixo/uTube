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
import com.topia.myapp.service.LikeDownService;


@Controller
@RequestMapping("/hate")
public class LikeDownController {
	
	@Autowired
	private LikeDownService likeDownService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insert(int postId, int memId) {
		return likeDownService.hate(postId, memId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/hateCnt", method = RequestMethod.GET)
	public int hateCnt(int postId) {
		return likeDownService.hateCnt(postId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkHate", method = RequestMethod.GET)
	public int checkHate(int postId, int memId) { //0 or 1 (count)
		return likeDownService.checkHate(postId, memId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(int postId, int memId) {
		return likeDownService.hateCancel(postId, memId);
	}
	
}
