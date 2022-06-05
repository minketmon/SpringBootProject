package com.hyundai.project.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: ClubMemberDTO 
 * @author			: 정예성 
 * @description		: 멤버 정보를 담을 DTO 클래스 
**/

@Data
@Setter
@Getter
public class ClubMemberDTO implements Serializable {
	private String email; // 이메일 
    private String password; // 비밀번호 
	private String name; // 이름 
	private int from_social; // 소셜 로그인 구분 0이면 일반, 1이면 소셜 로그인 
	private Date regdate, moddate; // 등록, 수정 날짜
	private ClubMemberRoleDTO role_set; // 권한 부여 
	
}//end class
