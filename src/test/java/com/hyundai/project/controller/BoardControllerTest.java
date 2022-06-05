package com.hyundai.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.hyundai.project.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName : com.hyundai.project.controller
 * @fileName : BoardControllerTest
 * @author : 고정민
 * @description : 게시판 controller JUnit 테스트
 **/

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// 게시글 작성 테스트
	@Test
	public void postInsertTest() throws Exception {
		BoardDTO dto = new BoardDTO();
		dto.setName("작성자");
		dto.setTitle("test title0");
		dto.setContent("test content0");
		dto.setPassword("1234");
		dto.setImage("none");
		ModelAndView mnv = mockMvc.perform(post("/insert").param("boardDTO", "dto"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();
		log.info(mnv.getViewName());// 이동한 페이지 출력
		log.info(mnv.getModel());// 모델에 담긴 값 출력
	}// end test

	// 게시글 조회 테스트
	@Test
	public void testlist() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/list").param("no", "18"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();

		log.info(mnv.getViewName()); // 어느 페이지로 갈건지 출력
		log.info(mnv.getModel()); // 모델에 담긴 값 출력

	}

	// 상세 조회 테스트
	@Test
	public void testDetail() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/detail").param("no", "18"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();

		log.info(mnv.getViewName()); // 어느 페이지로 갈건지 출력
		log.info(mnv.getModel()); // 모델에 담긴 값 출력
	}

	// 삭제 테스트
	@Test
	public void testDelete() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/delete").param("no", "25").param("title", "abccccc")
				.param("content", "ggggg").param("name", "ㅎㅇㅎ").param("password", "1111").param("image", "none")
				.param("uploadFiles", "none")).andReturn());
	}

	// 수정 테스트
	@Test
	public void testModify() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/update").param("no", "25").param("title", "abccccc")
				.param("content", "ggggg").param("name", "ㅎㅇㅎ").param("password", "1111").param("image", "none")
				.param("uploadFiles", "none")).andReturn());
	}

	// 첨부파일 조회
	@Test
	public void testDisplay() throws Exception {
		ModelAndView mnv = mockMvc
				.perform(get("/display").param("fileName",
						"/2022/05/16/85d887cd-de91-4612-8a90-ab598bc9880c_스크린샷 2022-05-14 오후 11.06.11.png"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getModelAndView();

		log.info(mnv.getViewName()); // 어느 페이지로 갈건지 출력
		log.info(mnv.getModel()); // 모델에 담긴 값 출력

	}

}// end class
