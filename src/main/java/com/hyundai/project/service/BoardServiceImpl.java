package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.BoardDAO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.CriteriaDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: BoardServiceImpl
 * @author			: 고정민
 * @description		: 게시판과 관련된 DB 호출을 관리하는 클래스
**/

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO BoardDAO;

	// 게시글 입력
	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		try {
			BoardDAO.insertBoard(boardDTO);
			log.info("게시물 입력 성공");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end insert..

	// 전체 게시글 조회
	@Override
	public List<BoardDTO> getBoardList() throws Exception {
		try {
			return BoardDAO.getBoardList();
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getAList...
	
	// 게시글 조회 [페이징]
	@Override
	public List<BoardDTO> getBoardListWithPaging(CriteriaDTO cri) throws Exception {
		try {
			return BoardDAO.getBoardListWithPaging(cri);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 게시글 상세 조회
	@Override
	public BoardDTO getDetail(long no) throws Exception {
		try {
			BoardDTO BoardDTO = BoardDAO.getDetail(no);
			if (BoardDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return BoardDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getDe..

	// 게시글 삭제
	@Override
	public void deleteBoard(BoardDTO boardDTO) throws Exception {
		try {
			if (BoardDAO.deleteBoard(boardDTO) == 0) {
				throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end delete..
	
	// 게시글 수정
	@Override
	public void updateBoard(BoardDTO BoardDTO) throws Exception {
		try {
			if (BoardDAO.updateBoard(BoardDTO) == 0) {
				throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end updateA..
	
	// 게시글 조회수 1증가
	@Override
	public void countBoard(long no) throws Exception {
		try {
			if(BoardDAO.countBoard(no) == 0) {
				throw new RuntimeException("게시물이 존재하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 게시글 총 개수 조회
	@Override
	public int getTotalCount(CriteriaDTO cri) throws Exception {
		try {
			log.info("get total count");
			return BoardDAO.getTotalCount(cri);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

}// end class
