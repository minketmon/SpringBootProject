package com.hyundai.project.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMemberDTO;
import com.hyundai.project.dto.ClubMemberUserDetailsDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.security.service
 * @fileName		: ClubMemberUserDetailService
 * @author			: 정예성
 * @description		: 일반 로그인 Security 처리를 위한 서비스  
**/

@Service
@Log4j2
public class ClubMemberUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ClubMemberDAO clubMemberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
		log.info("--loadUserByUsername----");
    	log.info(username);
    	
    	ClubMemberDTO result = null; 
    	
    	ClubMemberDTO dto = new ClubMemberDTO();
    	dto.setEmail(username);
    	
    	result = clubMemberDAO.findByEmail(dto);
    	
    	ClubMemberDTO result2 = result;
    	
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_" + result2.getRole_set()));
    	
    	ClubMemberUserDetailsDTO detailsDTO = 
    			new ClubMemberUserDetailsDTO(result2.getEmail(), result2.getPassword(), result2.getFrom_social(),
    					authorities);
    	detailsDTO.setName(result2.getName());
    	detailsDTO.setFromSocial(result2.getFrom_social());

    	return detailsDTO;
	}
}
