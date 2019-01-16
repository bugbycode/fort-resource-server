package com.bugbycode.service.network.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.network.NetworkDao;
import com.bugbycode.module.network.Network;
import com.bugbycode.service.network.NetworkService;
import com.util.page.Page;
import com.util.page.SearchResult;

@Service("networkService")
public class NetworkServiceImpl implements NetworkService {

	@Autowired
	private NetworkDao networkDao;
	
	@Override
	public SearchResult<Network> query(Map<String, Object> param, int startIndex, int pageSize) {
		SearchResult<Network> sr = new SearchResult<Network>();
		Page page = new Page(pageSize, startIndex);
		int totalCount = networkDao.count(param);
		List<Network> list = new ArrayList<Network>();
		if(totalCount > 0) {
			page.setTotalCount(totalCount);
			RowBounds rb = new RowBounds(page.getStartIndex(), page.getPageSize());
			list = networkDao.query(param, rb);
		}
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
	
	@Override
	public List<Network> query(Map<String, Object> param){
		return networkDao.query(param);
	}

	@Override
	public Network queryById(int id) {
		return networkDao.queryById(id);
	}

	@Override
	public Network queryByName(String name) {
		return networkDao.queryByName(name);
	}

	@Override
	public int insert(Network network) {
		int row = networkDao.insert(network);
		if(row > 0) {
			return network.getId();
		}
		return 0;
	}

	@Override
	public int update(Network network) {
		return networkDao.update(network);
	}

	@Override
	public int delete(int id) {
		return networkDao.delete(id);
	}

}
