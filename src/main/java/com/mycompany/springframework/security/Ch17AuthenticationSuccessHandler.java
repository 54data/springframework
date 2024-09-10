package com.mycompany.springframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch17AuthenticationSuccessHandler 
	extends SavedRequestAwareAuthenticationSuccessHandler { // 로그인 성공시 사용자가 요청한 페이지 이동
	//extends SimpleUrlAuthenticationSuccessHandler { // 로그인 성공시 지정한 페이지로 이동 
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication
		) throws ServletException, IOException {
		log.info("실행");
		// extends SimpleUrlAuthenticationSuccessHandler 사용할 경우
		// setDefaultTargetUrl("/"); // 로그인 성공시 이 경로로 강제 이동
		super.onAuthenticationSuccess(request, response, authentication); // 로그인 성공시 사용자가 요청한 페이지로 이동하기 위해 부모의 메소드를 호출시켜야 한다. (생략 X)
	}
}
