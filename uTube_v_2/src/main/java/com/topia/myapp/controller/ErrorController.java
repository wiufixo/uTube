package com.topia.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.topia.myapp.HomeController;


@Controller
@RequestMapping("/error")
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/accessError")
	public String accessError() {
		logger.info("*****************접근권한 ERROR*****************");
		return "error/accessError";
	}
	
	@RequestMapping("/detailError")
	public String detailError() {
		logger.info("*****************정보없음 ERROR*****************");
		return "error/detailError";
	}
	
}
