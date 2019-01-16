package com.bugbycode.dao.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.bugbycode.module.resource.Resource;

public interface ResourceDao {
	
	public List<Resource> query(Map<String,Object> param,RowBounds rb);
	
	public List<Resource> query(Map<String, Object> param);
	
	public int count(Map<String,Object> param);
	
	public Resource queryById(int id);
	
	public Resource queryByName(String name);
	
	public List<Resource> queryByIp(String ip);
	
	public List<Resource> queryByNetworkId(int networkId);
	
	public int insert(Resource r);
	
	public int update(Resource r);
	
	public int delete(int resId);
}
