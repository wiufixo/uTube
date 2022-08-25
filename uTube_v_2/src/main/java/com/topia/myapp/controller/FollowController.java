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


@Controller
@RequestMapping("/follow")
public class FollowController {
	
	@Autowired
	private FollowService followService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public int insert(int follower, int followed) {
		return followService.follow(follower, followed);
	}
	
	@ResponseBody
	@RequestMapping(value = "/followerCnt", method = RequestMethod.GET)
	public int followerCnt(int followed) {
		return followService.followerCnt(followed);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkFollow", method = RequestMethod.GET)
	public int checkFollow(int follower, int followed) { //0 or 1 (count)
		return followService.checkFollow(follower, followed);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int followCancel(int follower, int followed) {
		System.out.println(follower);
		System.out.println(followed);
		return followService.followCancel(follower, followed);
	}
	
}
