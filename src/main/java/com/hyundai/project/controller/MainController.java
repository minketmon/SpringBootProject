package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyundai.project.dto.ClubMemberDTO;
import com.hyundai.project.dto.ClubMemberRoleDTO;
import com.hyundai.project.service.ClubMemberService;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: MainController 
 * @author			: 정예성 
 * @description		: 로그인, 회원가입, 이메일 중복 확인 처리하는 컨트롤러
**/

@Controller
@Log4j2
@RequestMapping("/member/*")
public class MainController {
	
	@Autowired // 비밀번호 암호화 
	private PasswordEncoder passwordEncoder;
	
	@Autowired // 의존성 주입 
	private ClubMemberService clubMemberService;
	
	// /member/login으로 들어오는 요청을 처리하는 메서드
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error,
			@RequestParam(value = "exception", required = false)String exception, Model model) 
					throws Exception {
		// 에러 메세지와 예외 model로 넘겨주기(로그인 실패 handler 처리를 위해)
		model.addAttribute("error",error);
        model.addAttribute("exception",exception);
		log.info("로그인 페이지 진입......");
		return "login";
	}
	
	// /member/signup 페이지로 이동하는 메서드  
	@GetMapping("/signup")
	public String signup()throws Exception {
		return "signup";
	}
	
	// /member/signup 
	@PostMapping("/signup")
	public String signup(@ModelAttribute ClubMemberDTO dto) throws Exception {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		dto.setFrom_social(0);
		dto.setRole_set(ClubMemberRoleDTO.USER);
		int result = clubMemberService.insertClubMember(dto);
		if(result != 1) {
			return "redirect:/member/signup";
		}
		return "redirect:/member/login";
	}
	
	// 이메일 중복확인 
	@PostMapping("/emailChk")
	@ResponseBody
	public Map<String, Object> emailChk(@RequestBody Map<String, Object> params) { 
		int count = clubMemberService.emailChk((String)params.get("email"));
		Map<String, Object> map = new HashMap<>();
	
		map.put("count", count);
		return map;
	}
}
