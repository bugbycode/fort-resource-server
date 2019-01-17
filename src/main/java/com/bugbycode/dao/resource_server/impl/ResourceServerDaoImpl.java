package com.bugbycode.dao.resource_server.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.dao.resource_server.ResourceServerDao;
import com.bugbycode.module.resource_server.ResourceServer;

@Repository("resourceServerDao")
public class ResourceServerDaoImpl extends BaseDao implements ResourceServerDao {

	@Override
	public List<ResourceServer> query(int resId) {
		return getSqlSession().selectList("resourceServer.query", resId);
	}

	@Override
	public ResourceServer query(int resId, int serverType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("resId", resId);
		param.put("serverType", serverType);
		return getSqlSession().selectOne("resourceServer.queryByTypeAndResId", param);
	}

	@Override
	public void insert(ResourceServer server) {
		getSqlSession().insert("resourceServer.insert", server);
	}

	@Override
	public void update(ResourceServer server) {
		getSqlSession().insert("resourceServer.update", server);
	}

}
