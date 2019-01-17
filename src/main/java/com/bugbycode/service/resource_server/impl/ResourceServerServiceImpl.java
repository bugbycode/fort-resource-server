package com.bugbycode.service.resource_server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.resource_server.ResourceServerDao;
import com.bugbycode.module.resource_server.ResourceServer;
import com.bugbycode.service.resource_server.ResourceServerService;

@Service("resourceServerService")
public class ResourceServerServiceImpl implements ResourceServerService {

	@Autowired
	private ResourceServerDao resourceServerDao;
	
	@Override
	public List<ResourceServer> query(int resId) {
		return resourceServerDao.query(resId);
	}

	@Override
	public ResourceServer query(int resId, int serverType) {
		return resourceServerDao.query(resId, serverType);
	}

	@Override
	public void insert(ResourceServer server) {
		resourceServerDao.insert(server);
	}

	@Override
	public void update(ResourceServer server) {
		resourceServerDao.update(server);
	}

}
