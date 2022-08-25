package com.topia.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.topia.myapp.entity.Member;
import com.topia.myapp.service.FollowService;
import com.topia.myapp.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FollowService followService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Locale locale, Model model, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "all") String searchType) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("================게시물 리스트,갯수 페이지(home)로 Controller================");
		
		Member login = (Member) session.getAttribute("login");
		
		int memId = 0;
		
		if(login != null) {
			memId = login.getMemId();
		}
		
		model.addAttribute("postsCnt", postService.postsCnt(keyword, searchType));
		model.addAttribute("followingResult", followService.followingList(memId));
		//model.addAttribute("re", postService.postsList(20, 1, keyword, searchType));
		model.addAttribute("mainKeyword", keyword);
		model.addAttribute("mainSearchType", searchType);
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "post/home";
	}
	
}
