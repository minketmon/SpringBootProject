package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ReplyDAO;
import com.hyundai.project.dto.ReplyDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: BoardService
 * @author			: 고정민
 * @description		: 게시판과 관련된 DB 호출을 관리하는 클래스
**/

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyDAO mapper;
	
	// 댓글 전체 조회
	@Override
	public List<ReplyDTO> list(int bno) throws SQLException {
		log.info("get List.............");
		return mapper.list(bno);
	}//end list

	// 댓글 입력
	@Override
	public void insert(ReplyDTO vo) throws SQLException {
		log.info("insert.............");
		mapper.create(vo);
	}//end insert

	// 댓글 수정
	@Override
	public int update(ReplyDTO vo) throws SQLException {
		log.info("update.............");		
		
		return mapper.update(vo);
	}//end updtae

	// 댓글 삭제 
	@Override
	public int delete(int rno) throws SQLException {
		log.info("delete.............");	
		return mapper.delete(rno);
	}//end delete
	
}// end class
