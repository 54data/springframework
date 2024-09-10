package com.mycompany.springframework.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch13Account;
import com.mycompany.springframework.exception.Ch15AccountNotExistException;

@Service
public class Ch15AccountService {
	@Resource
	private Ch13AccountDao accountDao;
	
	public List<Ch13Account> getAccountList() {
		List<Ch13Account> list = accountDao.selectAll();
		return list;
	}
	
	public Ch13Account getAccount(int ano) {
		Ch13Account account = accountDao.selectByAno(ano);
		return account;
	}
	
	@Transactional
	public void transfer(int fromAno, int toAno, int amount) {	
		// 출금 작업
		Ch13Account fromAccount = accountDao.selectByAno(fromAno);
		if (fromAccount == null) {// fromAno에 해당하는 계좌가 있는지 확인
			throw new Ch15AccountNotExistException("출금 계좌 없음"); // 예외 발생
			// 예외가 발생을 하면 rollback을 해야 한다.
			// 출금이 안되니 입금도 안되도록 함 (트랜잭션)
		}
		fromAccount.setBalance(fromAccount.getBalance() - amount); // 새 잔고 만들기
		// 계좌가 있어서 정상적으로 실행 가능할 때
		accountDao.update(fromAccount); // 새 잔고로 업데이트 하기
		
		// 입금 작업
		Ch13Account toAccount = accountDao.selectByAno(toAno);
		if (toAccount == null) {
			throw new Ch15AccountNotExistException("입금 계좌 없음"); // 예외 발생
		}
		toAccount.setBalance(toAccount.getBalance() + amount); // 새 잔고 만들기
		// 계좌가 있어서 정상적으로 실행 가능할 때
		accountDao.update(toAccount); // 새 잔고로 업데이트 하기
	}
}
