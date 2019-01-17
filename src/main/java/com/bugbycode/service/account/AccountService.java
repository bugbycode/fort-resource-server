package com.bugbycode.service.account;

import java.util.List;

import com.bugbycode.module.account.Account;

public interface AccountService {
	
	public List<Account> query(int serverId);
	
	public Account queryByAccountAndServerId(String account,int serverId);
	
	public int insert(Account acc);
	
	public int update(Account acc);
	
	public void delete(int accId);
	
	public int countRel(int accId,int serverId); 
	
	public void insertRel(int accId,int serverId);
	
	public void deleteRel(int accId,int serverId);
}
