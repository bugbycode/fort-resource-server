package com.bugbycode.service.resource;

import java.util.List;
import java.util.Map;

import com.bugbycode.module.resource.Resource;
import com.util.page.SearchResult;

public interface ResourceService {
	
	public SearchResult<Resource> query(Map<String,Object> param,int startIndex,int pageSize);
	
	public Resource queryById(int id);
	
	public Resource queryByName(String name);
	
	public List<Resource> queryByIp(String ip);
	
	public List<Resource> queryByNetworkId(int networkId);
	
	public int insert(Resource r);
	
	public int update(Resource r);
	
	public int delete(int resId);
}
