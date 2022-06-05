package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.CriteriaDTO;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: BoardService
 * @author			: 고정민
 * @description		: 게시판과 관련된 DB 호출을 관리하는 인터페이스
**/

public interface BoardService {
	// 게시글 입력
	void insertBoard(BoardDTO boardDTO) throws Exception;
	
	// 게시글 조회 
	List<BoardDTO> getBoardList() throws Exception;
	
	// 게시글 조회 [페이징]
	List<BoardDTO> getBoardListWithPaging(CriteriaDTO cri) throws Exception;
	
	// 게시글 상세 조회
	BoardDTO getDetail(long no) throws Exception;
	
	// 게시글 삭제
	void deleteBoard(BoardDTO boardDTO) throws Exception;
	
	// 게시글 수정
	void updateBoard(BoardDTO boardDTO) throws Exception;
	
	// 게시글 조회수 1증가
	void countBoard(long no) throws Exception;
	
	// 게시글 총 개수 조회
	int getTotalCount(CriteriaDTO cri) throws Exception;

}//end int
