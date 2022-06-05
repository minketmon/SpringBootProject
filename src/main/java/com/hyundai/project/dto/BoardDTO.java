package com.hyundai.project.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: BoardDTO
 * @author			: 고정민
 * @description		: Board 테이블의 데이터를 매핑하기 위한 객체
**/

@Getter
@Setter
@ToString
public class BoardDTO implements Serializable {
	// 게시글 번호
	private long no;
	// 작성자 이름
	private String name;
	// 게시글 제목
	private String title;
	// 게시글 내용
	private String content;
	// 등록 시간
	private Date regdate;
	// 조회수
	private int readcount;
	// 게시글 비밀번호
	private String password;
	// 첨부 이미지
	private String image;

	// 비밀번호 인코딩
	public void setPassword(String password) {
		this.password = DigestUtils.sha512Hex(password);
	}//setP...
}//end class
