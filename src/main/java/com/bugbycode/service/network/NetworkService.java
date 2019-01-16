package com.bugbycode.service.network;

import java.util.List;
import java.util.Map;

import com.bugbycode.module.network.Network;
import com.util.page.SearchResult;

public interface NetworkService {
	
	public SearchResult<Network> query(Map<String, Object> param, int startIndex, int pageSize);
	
	public List<Network> query(Map<String, Object> param);
	
	public Network queryById(int id);
	
	public Network queryByName(String name);
	
	public int insert(Network network);
	
	public int update(Network network);
	
	public int delete(int id);
}
