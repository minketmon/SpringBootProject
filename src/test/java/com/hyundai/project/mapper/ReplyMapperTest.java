package com.hyundai.project.mapper;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.ReplyDAO;
import com.hyundai.project.dto.ReplyDTO;

/**
 * @packageName		: com.hyundai.project.mapper
 * @fileName		: ReplyMapperTest
 * @author			: 고정민
 * @description		: 댓글 관련 mapper JUint 테스트
**/

@SpringBootTest
public class ReplyMapperTest {

	@Autowired
	private ReplyDAO mapper;

	// 댓글 전체 조회 테스트
	@Test
	public void testGetList() throws SQLException {
		mapper.list(1).forEach(i -> System.out.println(i));
	}// end void

	// 댓글 작성 테스트
	@Test
	public void testInsert() throws SQLException {
		ReplyDTO reply = new ReplyDTO();
		reply.setBno(10); // db에서 확인후 진행
		reply.setReplytext("새로작성하는글");
		reply.setReplyer("newbie");
		mapper.create(reply);
		System.out.println(reply);

	}// end void

	// 댓글 수정 테스트
	@Test
	public void testUpdate() throws SQLException {
		ReplyDTO reply = new ReplyDTO();
		// 실행전 존재하는 번호인지 확인
		reply.setRno(7);
		reply.setReplytext("7수정된 내용");
		reply.setReplyer("user00");
		int count = mapper.update(reply);
		System.out.println("Update COUNT" + count);
	}

	// 댓글 삭제 테스트
	@Test
	public void testDelete() throws SQLException {
		// db에 데이터 있는지 확인
		System.out.println("Delete count: " + mapper.delete(16));
	}// end void

}// end class
