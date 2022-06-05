package com.hyundai.project.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.dto.ClubMemberDTO;
import com.hyundai.project.service.ClubMemberService;

/**
 * @packageName		: src.test.java
 * @fileName		: ClubMemberServiceTest 
 * @author			: 정예성 
 * @description		: ClubMemberService의 junit 테스트 실행 
**/

@SpringBootTest
public class ClubMemberServiceTest {
	@Autowired
	private ClubMemberService service;
	
	@Autowired // 비밀번호 암호화 
	private PasswordEncoder passwordEncoder;
	
	@Test // 이메일 중복 확인 테스트
	public void testEmailCheck() throws SQLException {
		int count = service.emailChk("wjddptjd014@naver.com");
		
		System.out.println(count);
	}
	
	@Test // 회원가입 테스트
	public void testInsert() throws SQLException {
		for(int i =1; i <= 101; i++) {
			ClubMemberDTO dto = new ClubMemberDTO();
			dto.setEmail("user" + i + "@hyundai.com");
			dto.setName("사용자" + i);
			dto.setFrom_social(0);
			dto.setPassword(passwordEncoder.encode("1234"));
			service.insertClubMember(dto);
		};//end int
	}
}
