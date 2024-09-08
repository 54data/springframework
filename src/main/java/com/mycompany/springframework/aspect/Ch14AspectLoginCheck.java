package com.mycompany.springframework.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14AspectLoginCheck {
	@Around("@annotation(com.mycompany.springframework.aspect.LoginCheckAOP)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메소드 호출 전에 실행하는 공통 코드
		// 로그인 체크를 위해 session에 login이라고 저장된 member 객체를 얻기
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();

		Object login = session.getAttribute("login");
		if (login == null) {
			// 로그인 되지 않은 경우
			return "redirect:/ch13/loginForm";
		} else {
			// 로그인 된 경우
			Object result = joinPoint.proceed();
			return result;
		}
	}
}
