package com.bugbycode.service.account.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.module.account.Account;
import com.bugbycode.service.account.AccountService;
import com.util.AESUtil;
import com.util.StringUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> query(int serverId) {
		List<Account> list = accountDao.query(serverId);
		if(!CollectionUtils.isEmpty(list)) {
			for(Account acc : list) {
				String password = acc.getPassword();
				if(StringUtil.isNotEmpty(password)) {
					password = AESUtil.decrypt(password);
				}
				acc.setPassword(password);
			}
		}
		return list;
	}
	
	@Override
	public Account queryById(int accountId) {
		return accountDao.queryById(accountId);
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

	@Override
	public int countRel(int accId, int serverId) {
		return accountDao.countRel(accId, serverId);
	}

	@Override
	public void insertRel(int accId, int serverId) {
		accountDao.insertRel(accId, serverId);
	}

	@Override
	public void deleteRel(int accId, int serverId) {
		accountDao.deleteRel(accId, serverId);
	}

	@Override
	public Account queryByAccountAndServerId(String account, int serverId) {
		Account acc = accountDao.queryByAccountAndServerId(account, serverId);
		if(acc != null) {
			String password = acc.getPassword();
			if(StringUtil.isNotEmpty(password)) {
				password = AESUtil.decrypt(password);
			}
			acc.setAccount(password);
		}
		return acc;
	}

}
