package com.hyundai.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import lombok.RequiredArgsConstructor;

/**
 * @packageName		: com.hyundai.project.config
 * @fileName		: SecurityConfig
 * @author			: 정예성
 * @description		: 스프링 시큐리티 설정 파일  
**/

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final AuthenticationFailureHandler customFailureHandler;
	
	@Bean // 회원가입시 비밀번호 암호화에 필요한 bean 등록
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..
	
	@Bean // 로그인 여부 & 권한 여부 확인하기 위한 bean 등록 
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

	@Bean
	public RoleHierarchyImpl roleHierarchyImpl() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
	    roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchyImpl;
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 화면별 권한 인가
		http.authorizeHttpRequests() 
				// 모든 사용자에게 권한 부여 
				.antMatchers("/list", "member/login", "/detail").permitAll()
				// USER 권한을 가진 사용자만
				.antMatchers("/delete", "/insert", "/update").hasRole("USER");
		
		// 인가 인증 문제시 로그인 화면
		http.formLogin()
				.loginPage("/member/login") // 커스텀 로그인 페이지 
				.loginProcessingUrl("/login") // form action url 
				.defaultSuccessUrl("/list") // 로그인 성공시 게시판 리스트 화면으로 
				.failureHandler(customFailureHandler); // 로그인 실패시 핸들러 처리
				
		//csrf 비활성화 
		http.csrf().disable();
		
		// 로그 아웃 세팅
		http.logout()
			.logoutUrl("/logout") // 로그아웃 href 경로 
			.logoutSuccessUrl("/member/login"); // 로그아웃 성공시 로그인 화면으로 이동
		
		// 소셜 로그인
		http.oauth2Login()
			.loginPage("/member/login") // 커스텀 로그인 페이지 
			.defaultSuccessUrl("/list"); // 성공시 게시판 리스트 화면으로		
	}
}
