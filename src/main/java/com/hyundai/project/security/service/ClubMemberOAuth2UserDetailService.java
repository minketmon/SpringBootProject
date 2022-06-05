package com.hyundai.project.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMemberDTO;
import com.hyundai.project.dto.ClubMemberRoleDTO;
import com.hyundai.project.dto.ClubMemberUserDetailsDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.security.service
 * @fileName		: ClubMemberOAuth2UserDetailService
 * @author			: 정예성
 * @description		: 소셜 로그인 Security 처리를 위한 서비스  
**/

@Service
@Log4j2
public class ClubMemberOAuth2UserDetailService 
	extends DefaultOAuth2UserService {

	@Autowired
	private ClubMemberDAO clubMemberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 소셜 로그인 계정 DB에 저장하기 위한 메서드
	private ClubMemberDTO saveSocialMember(String email) {
		
		ClubMemberDTO dto = new ClubMemberDTO();
		dto.setEmail(email);
		ClubMemberDTO result = clubMemberDAO.findByEmail(dto);
		
		// 이메일로 DB에 해당하는 유저 찾아서 null이 아니면 기존회원 
		if(result != null) {
			log.info("기존회원");
			return result;
		}
		
		// 비밀번호 1111로 세팅하고 DB로 INSERT
		ClubMemberDTO member = new ClubMemberDTO();
		member.setEmail(email);
		member.setFrom_social(1);
		member.setName(email);
		member.setPassword(passwordEncoder.encode("1111"));
		member.setRole_set(ClubMemberRoleDTO.USER);
		clubMemberDAO.insertClubMember(member);
		
		result = clubMemberDAO.findByEmail(member);
		// 추가된 정보를 반환
		return result;
	} 

    
   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest)
           throws OAuth2AuthenticationException {
	   log.info("-----loaduser---------------");
	   log.info("userRequest" + userRequest);
       
       String clientName = userRequest.getClientRegistration()
               .getClientName();
      
       //사용자 정보 가져오기 구글에서 허용한 API 범위
       OAuth2User oAuth2User = super.loadUser(userRequest);
       log.info("=====================");
       oAuth2User.getAttributes().forEach( ( k , v ) ->{
    	   log.info(k + " : " + v);
       });//end foreach
       
       // 신규회원 테이블에 저장 시작
       String email = null;
       
       if(clientName.equals("Google")) {
    	   email = oAuth2User.getAttribute("email");
    	   
       }
       
       // 신규회원 권한 세팅
       ClubMemberDTO clubMemberDTO = saveSocialMember(email);
       List<GrantedAuthority> authorities = new ArrayList<>();
       authorities.add(
               new SimpleGrantedAuthority("ROLE_" + clubMemberDTO.getRole_set()));      
       
       ClubMemberUserDetailsDTO detailsDTO = new ClubMemberUserDetailsDTO(clubMemberDTO.getEmail(), clubMemberDTO.getPassword(), 
    		   										1, authorities,oAuth2User.getAttributes());
       detailsDTO.setName(clubMemberDTO.getName());
       detailsDTO.setFromSocial(clubMemberDTO.getFrom_social());
       // 추가된 정보 반환
       return detailsDTO;
       
   }//end load..
}
