package com.mycompany.springframework.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch17UserDetailService implements UserDetailsService {
	@Autowired
	private Ch13MemberDao memberDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // username이라고 되어 있지만 id로 받으면 된다.
		Ch13Member member = memberDao.selectByMid(username);
		
		if (member == null) {
			throw new UsernameNotFoundException("Bad username");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(member.getMrole())); // GrantedAuthority 인터페이스를 구현한 케이스
		
		UserDetails userDetails = new Ch17UserDetails(member, authorities); 
		return userDetails; // UserDetails로 리턴되지 않으면 사용할 수 X
	}
}
