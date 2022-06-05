package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.CriteriaDTO;

/**
 * @packageName		: com.hyundai.project.dao
 * @fileName		: BoardDAO
 * @author			: 고정민
 * @description		: Board 테이블 data에 접근하기 위한 객체
**/

@Mapper
public interface BoardDAO {
	// 게시글 작성 
	void insertBoard(BoardDTO boardDTO) throws SQLException; 
	
	// 게시글 조회
	List<BoardDTO> getBoardList() throws SQLException;	

	// 게시글 조회 [페이징]
	List<BoardDTO> getBoardListWithPaging(CriteriaDTO criteria) throws SQLException;
	
	// 게시글 상세 조회
	BoardDTO getDetail(long no) throws SQLException;
	
	// 게시글 삭제
	int deleteBoard(BoardDTO boardDTO) throws SQLException;
	
	// 게시글 수정
	int updateBoard(BoardDTO BoardDTO) throws SQLException;
	
	// 게시글 조회수 1증가
	int countBoard(long no) throws SQLException;
	
	// 게시글 총 개수 조회 
	int getTotalCount(CriteriaDTO cri) throws SQLException;

}//end int..
