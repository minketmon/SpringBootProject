package com.hyundai.project.service;

import com.hyundai.project.dto.ClubMemberDTO;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: ClubMemberService
 * @author			: 정예성
 * @description		: 회원가입, 이메일 중복 체크를 위한 서비스 인터페이스 
**/

public interface ClubMemberService {
	// 회원가입
	public int insertClubMember(ClubMemberDTO clubMemberDTO);
	
	// 이메일 중복 체크 
	public int emailChk(String email);
}
