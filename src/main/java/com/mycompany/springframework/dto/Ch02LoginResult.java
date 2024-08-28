package com.mycompany.springframework.dto;

import lombok.Data;

@Data
public class Ch02LoginResult {
	private String result; // JSON의 속성이 된다.
	private String mid;
}
