package com.bugbycode.service.resource_server;

import java.util.List;

import com.bugbycode.module.resource_server.ResourceServer;

public interface ResourceServerService {

	public List<ResourceServer> query(int resId);
	
	public ResourceServer query(int resId,int serverType);
	
	public void insert(ResourceServer server);
	
	public void update(ResourceServer server);
}
