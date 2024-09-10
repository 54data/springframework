package com.mycompany.springframework.exception;

public class Ch15AccountNotExistException extends RuntimeException {
	public Ch15AccountNotExistException() {
	}
	
	public Ch15AccountNotExistException (String message) {
		super(message); // 메세지를 부모에게 전달
	}
}
