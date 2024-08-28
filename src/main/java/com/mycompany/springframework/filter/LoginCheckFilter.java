package com.mycompany.springframework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		log.info("전처리 실행 *****************");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse= (HttpServletResponse) response;

		String requestURI = httpServletRequest.getRequestURI(); // /springframework/ch02/mypage
		String requestURL = httpServletRequest.getRequestURL().toString(); // http://localhost:8080/springframework/ch02/mypage
		log.info("requestURI: " + requestURI);
		log.info("requestURL: " + requestURL);
		if (requestURI.contains("/mypage")) {
			HttpSession session = httpServletRequest.getSession();
			if (session.getAttribute("login") == null) { // login 키가 없을 때
				// 로그인을 안 한 상태 - LoginForm으로 redirect
				String contextPath = request.getServletContext().getContextPath(); // httpServletRequest.getContextPath()
				httpServletResponse.sendRedirect(contextPath + "/ch02/loginForm");
				return;
			}
		}
		
		// --------------------------------
		chain.doFilter(request, response);
		// --------------------------------
		
		// 후처리
		log.info("후처리 실행 *****************");
	}

}
