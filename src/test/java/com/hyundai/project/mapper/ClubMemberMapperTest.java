package com.hyundai.project.mapper;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMemberDTO;

/**
 * @packageName		: src.test.java
 * @fileName		: ClubMemberMapperTest 
 * @author			: 정예성 
 * @description		: ClubMemberDAO의 junit 테스트 실행 
**/

@SpringBootTest
public class ClubMemberMapperTest {
	@Autowired
	private ClubMemberDAO mapper;
	
	@Autowired // 비밀번호 암호화 
	private PasswordEncoder passwordEncoder;

	@Test // 로그인 테스트
	public void testFindEmail() throws SQLException {
		
		ClubMemberDTO dto = new ClubMemberDTO();
		dto.setEmail("test06@hyundai.com");
	
		System.out.println("=======================");
		System.out.println(mapper.findByEmail(dto));
		System.out.println("=======================");
	}
	
	@Test // 이메일 중복 확인 테스트
	public void testEmailCheck() throws SQLException {
		int count = mapper.emailChk("wjddptjd014@naver.com");
		
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
			mapper.insertClubMember(dto);
		};//end int
	}
}
