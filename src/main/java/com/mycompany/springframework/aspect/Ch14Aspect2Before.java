package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@Order(1)
public class Ch14Aspect2Before {
	// com.mycompany.springframework.controller.Ch14Controller에 있는 before로 시작하는 메소드 모두 실행하기 전에 아래의 method()를 먼저 실행
	@Before("execution(public * com.mycompany.springframework.controller.Ch14Controller.before*(..))") // public으로 시작하고 리턴 타입은 중요하지 않고 
	public void method() {
		// 메소드 호출 전에 실행하는 공통 코드
		log.info("실행");
		
	}
}
