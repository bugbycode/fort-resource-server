package com.bugbycode.dao.resource.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.dao.resource.ResourceDao;
import com.bugbycode.module.resource.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDao implements ResourceDao {

	@Override
	public List<Resource> query(Map<String, Object> param, RowBounds rb) {
		return getSqlSession().selectList("resource.query", param, rb);
	}

	@Override
	public int count(Map<String, Object> param) {
		return getSqlSession().selectOne("resource.count", param);
	}

	@Override
	public Resource queryById(int id) {
		return getSqlSession().selectOne("resource.queryById", id);
	}

	@Override
	public Resource queryByName(String name) {
		return getSqlSession().selectOne("resource.queryByName", name);
	}

	@Override
	public List<Resource> queryByIp(String ip) {
		return getSqlSession().selectList("resource.queryByIp", ip);
	}

	@Override
	public List<Resource> queryByNetworkId(int networkId) {
		return getSqlSession().selectList("resource.queryByNetworkId", networkId);
	}

	@Override
	public int insert(Resource r) {
		return getSqlSession().insert("resource.insert", r);
	}

	@Override
	public int update(Resource r) {
		return getSqlSession().update("resource.update", r);
	}

	@Override
	public int delete(int resId) {
		return getSqlSession().delete("resource.delete", resId);
	}

}
