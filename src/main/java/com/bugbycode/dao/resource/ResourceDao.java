package com.bugbycode.dao.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.bugbycode.module.resource.Resource;

public interface ResourceDao {
	
	public List<Resource> query(Map<String,Object> param,RowBounds rb);
	
	public int count(Map<String,Object> param);
	
	public Resource queryById(int id);
	
	public Resource queryByName(String name);
	
	public List<Resource> queryByIp(String ip);
}
