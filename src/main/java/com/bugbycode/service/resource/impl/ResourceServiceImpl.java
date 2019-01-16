package com.bugbycode.service.resource.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbycode.dao.resource.ResourceDao;
import com.bugbycode.module.resource.Resource;
import com.bugbycode.service.resource.ResourceService;
import com.util.page.Page;
import com.util.page.SearchResult;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public SearchResult<Resource> query(Map<String, Object> param, int startIndex, int pageSize) {
		SearchResult<Resource> sr = new SearchResult<Resource>();
		Page page = new Page(pageSize, startIndex);
		int totalCount = resourceDao.count(param);
		List<Resource> list = new ArrayList<Resource>();
		if(totalCount > 0) {
			page.setTotalCount(totalCount);
			RowBounds rb = new RowBounds(page.getStartIndex(), page.getPageSize());
			list = resourceDao.query(param, rb);
		}
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public Resource queryById(int id) {
		return resourceDao.queryById(id);
	}

	@Override
	public Resource queryByName(String name) {
		return resourceDao.queryByName(name);
	}

	@Override
	public List<Resource> queryByIp(String ip) {
		return resourceDao.queryByIp(ip);
	}

	@Override
	public List<Resource> queryByNetworkId(int networkId) {
		return resourceDao.queryByNetworkId(networkId);
	}

	@Override
	public int insert(Resource r) {
		int row = resourceDao.insert(r);
		if(row > 0) {
			return r.getId();
		}
		return 0;
	}

	@Override
	public int update(Resource r) {
		return resourceDao.update(r);
	}

	@Override
	public int delete(int resId) {
		return resourceDao.delete(resId);
	}

}
