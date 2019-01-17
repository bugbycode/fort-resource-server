package com.bugbycode.dao.resource_server;

import java.util.List;

import com.bugbycode.module.resource_server.ResourceServer;

public interface ResourceServerDao {
	
	public List<ResourceServer> query(int resId);
	
	public ResourceServer query(int resId,int serverType);
	
	public void insert(ResourceServer server);
	
	public void update(ResourceServer server);
}
