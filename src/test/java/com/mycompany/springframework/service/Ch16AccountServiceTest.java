package com.mycompany.springframework.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.springframework.WebAppTest;
import com.mycompany.springframework.dto.Ch13Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch16AccountServiceTest extends WebAppTest {
	@Autowired
	private Ch15AccountService accountService;
	
	@Test
	public void testGetAccountList() {
		List<Ch13Account> list = accountService.getAccountList();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testTransfter() {
		Ch13Account fromAccount1 = accountService.getAccount(1);
		Ch13Account toAccount1 = accountService.getAccount(2);
		int fromAccountBalance = fromAccount1.getBalance();
		int toAccountBalance = toAccount1.getBalance();
		
		accountService.transfer(1, 2, 1000);
		
		Ch13Account fromAccount2 = accountService.getAccount(1);
		Ch13Account toAccount2 = accountService.getAccount(2);
		
		Assert.assertEquals(fromAccountBalance - 1000, fromAccount2.getBalance());
		Assert.assertEquals(toAccountBalance + 1000, toAccount2.getBalance());
	}
}
