package com.bugbycode.dao.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bugbycode.dao.account.AccountDao;
import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.module.account.Account;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public List<Account> query(int serverId) {
		return getSqlSession().selectList("account.query", serverId);
	}
	
	@Override
	public Account queryById(int accountId) {
		return getSqlSession().selectOne("account.queryById", accountId);
	}

	@Override
	public Account queryByAccountAndServerId(String account,int serverId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("account", account);
		param.put("serverId", serverId);
		return getSqlSession().selectOne("account.queryByAccountAndServerId", param);
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

	@Override
	public int countRel(int accId, int serverId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("accountId", accId);
		param.put("serverId", serverId);
		return getSqlSession().selectOne("account.countRel", param);
	}

	@Override
	public void insertRel(int accId, int serverId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("accountId", accId);
		param.put("serverId", serverId);
		getSqlSession().insert("account.insertRel", param);
	}

	@Override
	public void deleteRel(int accId, int serverId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("accountId", accId);
		param.put("serverId", serverId);
		getSqlSession().delete("account.deleteRel", param);
	}

}
