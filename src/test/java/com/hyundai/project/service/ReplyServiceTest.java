package com.hyundai.project.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hyundai.project.dto.*;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: ReplyServiceTest
 * @author			: 고정민
 * @description		: 댓글 관련 service JUint 테스트
**/

@SpringBootTest
public class ReplyServiceTest {
	
	@Autowired
	private ReplyService  service;
	
	// 전체 댓글 조회 테스트
	@Test
	public void testExists() throws SQLException {
		System.out.println(service);
		service.list(1).forEach(
				i -> System.out.println(i)
			);	
	}//end test
	
	// 댓글 작성 테스트
	@Test
	public void testInsert() throws SQLException {
		System.out.println(service);
		ReplyDTO board = new ReplyDTO() ;
		board.setBno(11); //db에서 확인후 진행		
		board.setReplytext("서비스 새로작성하는글");
		board.setReplyer("newbie");
		service.insert(board);
		System.out.println(board);
	}//end test
	
	// 댓글 수정 테스트
	@Test
	public void testUpdate() throws SQLException {
		ReplyDTO board = new ReplyDTO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(6);
		board.setReplytext("service 수정된 내용");
		board.setReplyer("user00");		
		int count = service.update(board);
		System.out.println("Update COUNT" + count);
	}//end test

	// 댓글 삭제 테스트
	@Test
	public void testDelete() throws SQLException {
		System.out.println(service);
		//디비에 있는지 확인
		int count = service.delete(4);
		System.out.println("Delete COUNT" + count);
	}//end test



}//end clas
