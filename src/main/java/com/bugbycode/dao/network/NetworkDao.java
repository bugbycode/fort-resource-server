package com.bugbycode.dao.network;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.bugbycode.module.network.Network;

public interface NetworkDao {
	
	public List<Network> query(Map<String,Object> param,RowBounds rb);
	
	public int count(Map<String,Object> param);
	
	public Network queryById(int id);
	
	public Network queryByName(String name);
	
	public int insert(Network network);
	
	public int update(Network network);
	
	public int delete(int id);
}
