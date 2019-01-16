package com.bugbycode.dao.account.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.module.account.Account;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public List<Account> query(int resId) {
		return getSqlSession().selectList("account.query", resId);
	}

	@Override
	public int insert(Account acc) {
		return getSqlSession().insert("account.insert", acc);
	}

	@Override
	public int update(Account acc) {
		return getSqlSession().update("account.update", acc);
	}

	@Override
	public void delete(int accId) {
		getSqlSession().delete("account.delete", accId);
	}

}
