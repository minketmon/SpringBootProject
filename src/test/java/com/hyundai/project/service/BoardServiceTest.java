package com.hyundai.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.CriteriaDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: BoardServiceTest
 * @author			: 고정민
 * @description		: 게시물 관련 service JUint 테스트
**/
@Log4j2
@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;
	
	// 게시글 입력 테스트
	@Test
	public void testInsert() throws Exception {
		System.out.println(service);
		BoardDTO board = new BoardDTO();
		board.setNo(20);
		board.setTitle("제목");
		board.setContent("내용");
		board.setName("이름");
		board.setPassword("0000");
		board.setImage("none");
		service.insertBoard(board);
	}//end test
	
	// 게시글 수정 테스트
	@Test
	public void testUpdate() throws Exception {
		BoardDTO board = new BoardDTO() ;
		//실행전 존재하는 번호인지 확인
		board.setNo(20);
		board.setTitle("제목");
		board.setContent("내용");
		board.setName("이름");
		board.setPassword("0000");
		board.setImage("none");
		service.updateBoard(board);
	}//end test

	// 게시글 삭제 테스트
	@Test
	public void testDelete() throws Exception {
		System.out.println(service);
		//디비에 있는지 확인
		BoardDTO board = new BoardDTO() ;
		board.setNo(20);
		service.deleteBoard(board);
	}//end test

	// 게시글 조회 테스트
	@Test
	public void testGet() throws Exception {
		log.info(service.getBoardList());
	}
	
	// 게시글 페이지 별 조회 테스트
	@Test
	public void testGetPaging() throws Exception {
		CriteriaDTO cri = new CriteriaDTO();
		log.info(service.getBoardListWithPaging(cri));
	}
	
	// 게시글 상세조회 테스트
	@Test
	public void testDetail() throws Exception {
		log.info(service.getDetail(10));
	}
	
	// 게시글 조회수 1증가 테스트
	@Test
	public void testCount() throws Exception {
		service.countBoard(22);
	}
	
	// 게시글 총 개수 조회 테스트
	@Test
	public void testTotalCount() throws Exception {
		CriteriaDTO cri = new CriteriaDTO();
		log.info(service.getTotalCount(cri));
	}

}//end clas
