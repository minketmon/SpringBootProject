package com.hyundai.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: src.test.java
 * @fileName		: ClubMemberControllerTest 
 * @author			: 정예성 
 * @description		: ClubMemberController의 junit 테스트 실행 
**/

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class ClubeMemberControllerTest {
	
	@Autowired
	MainController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser
	@Test
	public void testlist() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/list").param("no", "18"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();

		log.info(mnv.getViewName()); // 어느 페이지로 갈건지 출력
		log.info(mnv.getModel()); // 모델에 담긴 값 출력
	}
}
