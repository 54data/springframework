package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect5AfterThrowing {
	@AfterThrowing(
		pointcut="execution(public * com.mycompany.springframework.controller.Ch14Controller.afterThrowing*(..))",
		throwing="e" // e는 변수 이름
	)
	public void method(Throwable e) { // 예외 객체를 파라미터로 받음
		log.info("실행");
		log.info("예외 메시지 : " + e.getMessage());
	}
}
