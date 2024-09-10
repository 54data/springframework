package com.mycompany.springframework.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.springframework.WebAppTest;
import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch13Account;

public class Ch16AccountDaoTest extends WebAppTest {
	@Autowired
	private Ch13AccountDao accountDao;
	
	@Test // Ch13AccountDao에 정의된 메소드들 각각 test
	public void testSelectAll() {
		List<Ch13Account> list = accountDao.selectAll();
		Assert.assertNotNull(list); // list는 null이 아니여야 한다.
		Assert.assertEquals(2, list.size()); // 실제 행이 2개이기 때문에 사이즈가 맞는지 확인
	}
	
	@Test
	public void testSelectByAno() {
		Ch13Account account = accountDao.selectByAno(1);
		Assert.assertNotNull(account);
		Assert.assertEquals(1, account.getAno());
		Assert.assertNotNull(account.getOwner());
		Assert.assertNotNull(account.getBalance());
	}
	
	@Test
	public void testUpdate() {
		Ch13Account account1 = accountDao.selectByAno(1);
		account1.setBalance(account1.getBalance() + 1000);
		accountDao.update(account1);
		
		Ch13Account account2 = accountDao.selectByAno(1);
		Assert.assertEquals(account1.getBalance(), account2.getBalance());
	}
}
