package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: BoardRestController
 * @author			: 고정민
 * @description		: 게시판 조회 수 1씩 증가시키는 Rest Controller
**/

@Log4j2
@RestController
@Controller
public class BoardRestController {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	// 해당 컨트롤러 진입할 때마다 DB에서 해당 게시글에 대한 조회수를 1씩 올려
	@PostMapping("/count")
	public void count(@RequestParam long no) {
		try {
			service.countBoard(no);
			log.info("count Controller");
		} catch (Exception e) {
			
		}
	}


}// end class
