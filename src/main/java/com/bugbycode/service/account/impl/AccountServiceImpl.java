package com.bugbycode.service.account.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.module.account.Account;
import com.bugbycode.service.account.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> query(int resId) {
		return accountDao.query(resId);
	}

	@Override
	public int insert(Account acc) {
		int row = accountDao.insert(acc);
		if(row > 0) {
			return acc.getId();
		}
		return 0;
	}

	@Override
	public int update(Account acc) {
		return accountDao.update(acc);
	}

	@Override
	public void delete(int accId) {
		accountDao.delete(accId);
	}

}
