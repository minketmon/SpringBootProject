package com.hyundai.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMemberDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.service
 * @fileName		: ClubMemberServiceImpl
 * @author			: 정예성
 * @description		: 회원가입, 이메일 중복 체크를 위한 서비스 구현체
**/

@Service
@Log4j2
public class ClubMemberServiceImpl implements ClubMemberService {

	@Autowired // 의존성 주입 
	ClubMemberDAO clubMemberDAO;
	
	@Override // 회원가입 
	public int insertClubMember(ClubMemberDTO clubMemberDTO) {
		try {
			clubMemberDAO.insertClubMember(clubMemberDTO);
	      // 오류 발생하면 0반환
		} catch(Exception e) {
			log.info(e.getStackTrace());
			return 0;
		}
		// 완료되면 1반환 
		return 1;
	}

	@Override // 이메일 중복 체크
	public int emailChk(String email) {
		// 중복되는 이메일 개수 
		int cnt = clubMemberDAO.emailChk(email);
		
		return cnt;
	}

}
