package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import com.hyundai.project.dto.*;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: ReplyService
 * @author			: 고정민
 * @description		: 댓글 관련된 DB 호출을 관리하는 인터페이스
**/

public interface ReplyService {
	// 전체 댓글 조회
	public List<ReplyDTO> list( int bno) throws SQLException;
	
	// 댓글 입력
	public void insert(ReplyDTO dto)throws SQLException;

	// 댓글 수정
	public int update(ReplyDTO dto) throws SQLException;

	// 댓글 삭제
	public int delete(int rno) throws SQLException;

}// end int
