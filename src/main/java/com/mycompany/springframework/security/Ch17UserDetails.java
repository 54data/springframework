package com.mycompany.springframework.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mycompany.springframework.dto.Ch13Member;

public class Ch17UserDetails extends User {
	private Ch13Member member;
	
	public Ch17UserDetails( 
			Ch13Member member,
			List<GrantedAuthority> authorities) {
		super(
				member.getMid(), member.getMpassword(), member.isMenabled(), 
				true, true, true, 
				authorities
		); // User가 가지고 있는 생성자를 호출
		// Controller, Service에서 현재 로그인한 사용자의 정보를 알아낼 때 사용함
		this.member = member;
	}
	
	public Ch13Member getMember() {
		return member;
	}
}
