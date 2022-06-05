package com.hyundai.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ClubMemberDTO;

/**
 * @packageName		: com.hyundai.project.dao
 * @fileName		: ClubMemberDAO
 * @author			: 정예성
 * @description		: 로그인, 회원가입, 이메일 중복 체크 수행을 위한 DAO   
**/

@Mapper
public interface ClubMemberDAO {
	
	// 로그인
	public ClubMemberDTO findByEmail(ClubMemberDTO clubMemberDTO);
	
	// 회원가입
	public int insertClubMember(ClubMemberDTO clubMemberDTO);
	
	// 이메일 중복 체크 
	public int emailChk(String email);
}//end interface
