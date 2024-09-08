package com.mycompany.springframework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14AspectRuntimeCheck {
	@Around("@annotation(com.mycompany.springframework.aspect.RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메소드 호출 전에 실행하는 공통 코드
		long start = System.nanoTime();

		Object result = joinPoint.proceed();

		// 메소드 호출 후에 실행하는 공통 코드
		long end = System.nanoTime();
		
		long howlong = end - start;
		
		// 실제 실행된 메소드 이름 = @RuntimeCheck 어노테이션이 붙은 메소드 이름을 알아내기
		String methodName = joinPoint.getSignature().toShortString();
		
		 log.info("실행 시간: (" + methodName + "): " + howlong + "ns");
		return result;
	}
}
