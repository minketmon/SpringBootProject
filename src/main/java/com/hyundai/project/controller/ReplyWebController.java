package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: ReplyWebController
 * @author			: 고정민
 * @description		: 해당 번호에 해당하는 게시물 댓글 출력하는 페이지
**/

@Log4j2
@Controller
@RequestMapping("/replies")
public class ReplyWebController {
	
	@GetMapping(value = "test")
	public String insert() {
		log.info("test");
		return "replies/test";
	}//end insert

}
