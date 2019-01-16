package com.bugbycode.dao.network.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.bugbycode.dao.base.BaseDao;
import com.bugbycode.dao.network.NetworkDao;
import com.bugbycode.module.network.Network;

@Repository("networkDao")
public class NetworkDaoImpl extends BaseDao implements NetworkDao {

	@Override
	public List<Network> query(Map<String, Object> param, RowBounds rb) {
		return getSqlSession().selectList("network.query", param, rb);
	}

	@Override
	public int count(Map<String, Object> param) {
		return getSqlSession().selectOne("network.count", param);
	}
	
	@Override
	public List<Network> query(Map<String, Object> param){
		return getSqlSession().selectList("network.query", param);
	}

	@Override
	public Network queryById(int id) {
		return getSqlSession().selectOne("network.queryById", id);
	}

	@Override
	public Network queryByName(String name) {
		return getSqlSession().selectOne("network.queryByName", name);
	}

	@Override
	public int insert(Network network) {
		return getSqlSession().insert("network.insert", network);
	}

	@Override
	public int update(Network network) {
		return getSqlSession().update("network.update", network);
	}

	@Override
	public int delete(int id) {
		return getSqlSession().delete("network.delete", id);
	}

}
