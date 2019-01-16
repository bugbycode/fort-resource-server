package com.bugbycode.service.account;

import java.util.List;

import com.bugbycode.module.account.Account;

public interface AccountService {
	
	public List<Account> query(int resId);
	
	public int insert(Account acc);
	
	public int update(Account acc);
	
	public void delete(int accId);
}
