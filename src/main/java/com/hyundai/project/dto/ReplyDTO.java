package com.hyundai.project.dto;

import java.util.Date;

import lombok.Data;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: ReplyDTO
 * @author			: 고정민
 * @description		: Reply 테이블의 데이터를 매핑하기 위한 객체
**/

@Data
public class ReplyDTO {
	// 댓글 번호
	private int rno;
	// 게시글 번호
	private int bno;
	// 댓글 내용
	private String replytext;
	// 댓글 작성자
	private String replyer;
	// 댓글 등록 시간
	private Date regdate;
	// 수정 시간
	private Date updatedate;

}// end class
