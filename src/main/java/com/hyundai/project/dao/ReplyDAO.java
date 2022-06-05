package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.*;

/**
 * @packageName		: com.hyundai.project.dao
 * @fileName		: ReplyDAO
 * @author			: 고정민
 * @description		: Reply 테이블 data에 접근하기 위한 객체
**/

@Mapper
public interface ReplyDAO {
	// 댓글 조회
	public List<ReplyDTO> list(int bno) throws SQLException;

	// 댓글 작성
	public void create(ReplyDTO vo) throws SQLException;

	// 댓글 수정
	public int update(ReplyDTO vo) throws SQLException;

	// 댓글 삭제 
	public int delete(int rno) throws SQLException;
}// end int..
