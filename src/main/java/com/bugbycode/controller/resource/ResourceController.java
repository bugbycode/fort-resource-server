package com.bugbycode.controller.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.network.Network;
import com.bugbycode.module.resource.Resource;
import com.bugbycode.service.network.NetworkService;
import com.bugbycode.service.resource.ResourceService;
import com.util.StringUtil;
import com.util.page.SearchResult;
import com.util.reg.RegexUtil;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private NetworkService networkService;
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(
			@RequestParam(name="keyword",defaultValue="")
			String keyWord,
			@RequestParam(name="startIndex",defaultValue="-1")
			int startIndex,
			@RequestParam(name="pageSize",defaultValue="10")
			int pageSize) {
		JSONObject json = new JSONObject();
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtil.isNotBlank(keyWord)) {
			param.put("keyword", keyWord);
		}
		if(startIndex > -1) {
			SearchResult<Resource> sr = resourceService.query(param, startIndex, pageSize);
			json.put("data", sr.getList());
			json.put("page", sr.getPage());
		}else {
			json.put("data", resourceService.query(param));
		}
		return json.toString();
	}
	
	@RequestMapping("/queryNetWork")
	@ResponseBody
	public String queryNetWork(
			@RequestParam(name="name",defaultValue="")
			String name,
			@RequestParam(name="startIndex",defaultValue="-1")
			int startIndex,
			@RequestParam(name="pageSize",defaultValue="10")
			int pageSize) {
		JSONObject json = new JSONObject();
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtil.isNotBlank(name)) {
			param.put("name", name);
		}
		if(startIndex > -1) {
			SearchResult<Network> sr = networkService.query(param, startIndex, pageSize);
			json.put("data", sr.getList());
			json.put("page", sr.getPage());
		}else {
			json.put("data", networkService.query(param));
		}
		return json.toString();
	}
	
	@RequestMapping("/queryById")
	@ResponseBody
	public String queryById(int resId) {
		JSONObject json = new JSONObject();
		Resource r = resourceService.queryById(resId);
		json.put("data", r);
		return json.toString();
	}
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public String queryByName(String name) {
		JSONObject json = new JSONObject();
		Resource r = resourceService.queryByName(name);
		json.put("data", r);
		return json.toString();
	}
	
	@RequestMapping("/queryByIp")
	@ResponseBody
	public String queryByIp(String ip) {
		JSONObject json = new JSONObject();
		List<Resource> list = resourceService.queryByIp(ip);
		json.put("data", list);
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Resource r = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Resource.class);
		String name = r.getName();
		String ip = r.getIp();
		int code = 0;
		String msg = "新建成功";
		try {
			if(!RegexUtil.check(RegexUtil.RESOURCE_NAME_REGEX, name)) {
				throw new RuntimeException("资源名称格式错误");
			}
			
			if(StringUtil.isEmpty(ip)) {
				throw new RuntimeException("资源IP格式错误");
			}
			
			Resource tmp = resourceService.queryByName(name);
			if(tmp != null) {
				throw new RuntimeException("资产名称已被使用");
			}
			
			resourceService.insert(r);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		int resId = r.getId();
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("resId", resId);
		json.put("code", code);
		return json.toJSONString();
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(String jsonStr) {
		Resource r = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Resource.class);
		int resId = r.getId();
		String name = r.getName();
		String ip = r.getIp();
		int code = 0;
		String msg = "修改成功";
		try {
			
			if(resId <= 0) {
				throw new RuntimeException("资源ID错误");
			}
			
			Resource res = resourceService.queryById(resId);
			if(res == null) {
				throw new RuntimeException("资源ID错误");
			}
			
			if(!RegexUtil.check(RegexUtil.RESOURCE_NAME_REGEX, name)) {
				throw new RuntimeException("资源名称格式错误");
			}
			
			if(StringUtil.isEmpty(ip)) {
				throw new RuntimeException("资源IP格式错误");
			}
			
			Resource tmp = resourceService.queryByName(name);
			if(!(tmp == null || tmp.getId() == resId)) {
				throw new RuntimeException("资产名称已被使用");
			}
			
			resourceService.update(r);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toJSONString();
	}
	
	/**
	 * 删除用户信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int resId) {
		int code = 0;
		String msg = "删除成功";
		try {
			if(resId <= 0) {
				throw new RuntimeException("资产ID信息错误");
			}
			Resource r = resourceService.queryById(resId);
			if(r == null) {
				throw new RuntimeException("该资产信息不存在");
			}
			resourceService.delete(resId);
		}catch (Exception e) {
			msg = e.getMessage();
			code = 1;
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toJSONString();
	}
}
