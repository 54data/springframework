package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {
	// 정적 메소드
	// static이 있으면 getInstance()로 객체 생성 가능
	public static Ch12Service2 getInstance() {
		log.info("실행");
		return new Ch12Service2(); // 이 객체를 관리 객체로 만듦
	}

	// 인스턴스 메소드 => 앞에 static 없을 시 Ch12Service2 객체가 생성하고 나서 getCh12Service3()를 호출함
	public Ch12Service3 getCh12Service3() {
		return new Ch12Service3(); // 이 객체를 관리 객체로 만듦
	}
}
