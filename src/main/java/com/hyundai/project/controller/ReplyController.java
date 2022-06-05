package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.service.ReplyService;
import com.hyundai.project.dto.*;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: ReplyController
 * @author			: 고정민
 * @description		: 댓글 조회/등록/삭제/수정 기능 처리하는 RestController
**/

@Log4j2
@RestController
@Controller
@RequestMapping("/replies")
public class ReplyController {
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	// ReplyDTO 객체를 받아와 댓글 등록하는 기능 처리
	@PostMapping(value = "")
	public ResponseEntity<String> register(
			@RequestBody ReplyDTO vo) {
		ResponseEntity<String> entry = null;
		try {
			service.insert(vo);
			entry = new ResponseEntity<String>(
					"SUCCESS", HttpStatus.OK);
			log.info("---register------");
			log.info(entry);
		
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}//end try
		return entry;
	}// end register..

	// 게시글 번호에 해당하는 댓글 전부 반환
	@GetMapping(value = "all/{bno}")
	public ResponseEntity<List<ReplyDTO>> list(
			@PathVariable("bno") int bno) {
		ResponseEntity<List<ReplyDTO>> entry = null;
		try {
			entry = new ResponseEntity<List<ReplyDTO>>(
					service.list(bno), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ReplyDTO>>(
					HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list

	// 전달받은 번호에 해당하는 댓글 수정 처리
	@RequestMapping(value = "/{rno}", 
			method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> update(
			@PathVariable("rno") int rno, @RequestBody ReplyDTO vo) {
		ResponseEntity<String> entry = null;
		try {
			vo.setRno(rno);
			service.update(vo);
			log.info("update");
			entry = new ResponseEntity<String>(
					"SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}//end try
		return entry;
	}// end update

	// 전달받은 번호에 해당하는 댓글 삭제 처리
	@RequestMapping(value = "/{rno}", 
			method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		ResponseEntity<String> entry = null;
		try {
			service.delete(rno);
			log.info("delete....");
			entry = new ResponseEntity<String>(
					"SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(
					e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}// end remove

}// end class
