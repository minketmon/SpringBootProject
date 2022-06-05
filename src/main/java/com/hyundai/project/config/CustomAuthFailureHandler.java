package com.hyundai.project.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @packageName		: com.hyundai.project.config
 * @fileName		: CustomAuthFailureHandler
 * @author			: 정예성
 * @description		: 로그인 실패시 처리과정 구현  
**/

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	AuthenticationException exception) throws IOException, ServletException {
		
		// 로그인 실패시 출력하는 메세지 설정
		String msg = "Invaild Username or Password";

		// 자격증명 실패(아이디 혹은 비밀번호 불일치)일 경우 
        if(exception instanceof BadCredentialsException){
          // 세션 만료된 경우 	
        } else if(exception instanceof InsufficientAuthenticationException){
            msg = "Invalid Secret Key";
        }
        
        // 로그인 실패시 해당 페이지로 에러 메세지와 함께 이동
        setDefaultFailureUrl("/member/login?error=true&exception="+msg);

        super.onAuthenticationFailure(request,response,exception);
	}
}
