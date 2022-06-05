package com.hyundai.project.mapper;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.BoardDAO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.CriteriaDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.mapper
 * @fileName		: BoardMapperTest
 * @author			: 고정민
 * @description		: 게시판 관련 mapper JUint 테스트
**/

@Log4j2
@SpringBootTest
public class BoardMapperTest {

	@Autowired
	private BoardDAO mapper;

	// 입력 테스트
	@Test
	public void insertTest() throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setNo(20);
		board.setTitle("제목");
		board.setContent("내용");
		board.setName("이름");
		board.setPassword("0000");
		board.setImage("none");
		mapper.insertBoard(board);
	}
	
	// 전체 조회 테스트
	@Test
	public void getTest() throws SQLException {
		log.info(mapper.getBoardList());
	}
	
	// 페이징 조회 테스트
	@Test
	public void pagingTest() throws SQLException {
		CriteriaDTO cri = new CriteriaDTO();
		log.info(cri);
		List<BoardDTO> list = mapper.getBoardListWithPaging(cri);
		
		log.info(list);
	}
	
	// 상세 조회 테스트
	@Test
	public void getDetail() throws SQLException  {
		long no = 1;
		log.info(mapper.getDetail(no));
	}
	
	// 삭제 테스트
	@Test
	public void delete() throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setNo(20);
		log.info(mapper.deleteBoard(board));
	}
	
	// 수정 테스트
	@Test
	public void update() throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setNo(13);
		board.setTitle("제목");
		board.setContent("내용");
		board.setName("이름");
		board.setPassword("0000");
		board.setImage("none");
		log.info(mapper.updateBoard(board));
	}
	
	// 조회수 카운트 테스트
	@Test
	public void count() throws SQLException {
		long no = 23;
		log.info(mapper.countBoard(no));
	}
	
	// 총 게시글 조회 테스트
	@Test
	public void totalCount() throws SQLException {
		CriteriaDTO cri = new CriteriaDTO();
		log.info(cri);
		log.info(mapper.getTotalCount(cri));
	}

	
}// end class
