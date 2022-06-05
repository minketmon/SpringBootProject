package com.hyundai.project.dto;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: ClubMemberUserDetailsDTO
 * @author			: 정예성
 * @description		: 로그인한 사용자의 정보를 담기위한 DTO 클래스   
**/

@Setter
@Getter
@ToString
public class ClubMemberUserDetailsDTO extends User implements OAuth2User{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private int fromSocial;
	private Map<String, Object> OA2; // 소셜 로그인 정보를 넘기기 위한 Map 변수  
	
	// 구성자 설정 
	public ClubMemberUserDetailsDTO(String username, String password, int fromSocial,
			List<GrantedAuthority> authorities) {
		// password는 부모 클래스 사용
		super(username, password, authorities);
		this.email = username;
		this.fromSocial = fromSocial;
	}
	
	public ClubMemberUserDetailsDTO(String username, String password, int fromSocial,
			List<GrantedAuthority> authorities, Map<String, Object> OA2) {
		this(username, password, fromSocial, authorities);
		this.OA2 = OA2;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return OA2;
	}
}
