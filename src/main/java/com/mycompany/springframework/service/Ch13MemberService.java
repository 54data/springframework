package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13MemberService {
	public enum JoinResult {
		SUCCESS,
		FAIL_DUPLICATED_MID
	}
	
	public enum LoginResult {
		SUCCESS, 
		FAIL_MID,
		FAIL_MPASSWORD,
		FAIL_ENABLED
	}
	
	@Resource
	private Ch13MemberDao memberDao;
	
	public JoinResult join(Ch13Member member) {// member 테이블에 대한 dto 가져오기
		// 유효성 검사는 다 controller
		boolean exist = isMid(member.getMid());
		if (exist) { // 기존에 mid가 존재하는지 확인
			return JoinResult.FAIL_DUPLICATED_MID;  
		}
		memberDao.insert(member); // join은 기존에 mid가 존재하면 insert가 안된다.
		return JoinResult.SUCCESS;
	}
	
	public boolean isMid(String mid) {
		Ch13Member member = memberDao.selectByMid(mid);
		if (member == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public LoginResult login(Ch13Member member){
		Ch13Member dbMember = memberDao.selectByMid(member.getMid());
		
		if (dbMember == null) {
			return LoginResult.FAIL_MID;
		} 
		
		if (!dbMember.isMenabled()) {
			return LoginResult.FAIL_ENABLED;
		}
		
		if (!dbMember.getMpassword().equals(member.getMpassword())) {
			return LoginResult.FAIL_MPASSWORD;
		}
		
		return LoginResult.SUCCESS;
	}
	
	public void logout(String mid) { // session 방식으로 할거면 여기서 말고 Controller
		
	}
}
