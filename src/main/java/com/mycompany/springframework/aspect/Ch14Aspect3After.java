package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect3After {
	// com.mycompany.springframework.controller.Ch14Controller에 있는 after로 시작하는 메소드 모두 실행하고 나서 아래의 method()를 실행
	@After("execution(public * com.mycompany.springframework.controller.Ch14Controller.after*(..))")
	public void method() {
		// 메소드 호출 후에 실행하는 고통 코드
		log.info("실행");
		
	}
}
