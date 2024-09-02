package com.mycompany.springframework.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//@Service // 관리 객체로 만들기 
@Slf4j
public class Ch12Service1 {
	// 기본 생성자
	public Ch12Service1() {
		log.info("실행");
	}
	
}
