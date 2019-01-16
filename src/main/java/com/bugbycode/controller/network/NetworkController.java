package com.bugbycode.controller.network;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.network.Network;
import com.bugbycode.service.network.NetworkService;
import com.util.StringUtil;
import com.util.page.SearchResult;
import com.util.reg.RegexUtil;

@Controller
@RequestMapping("/network")
public class NetworkController {
	
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
	public String queryById(int networkId) {
		JSONObject json = new JSONObject();
		Network network = networkService.queryById(networkId);
		json.put("data", network);
		return json.toString();
	}
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public String queryByName(String name) {
		JSONObject json = new JSONObject();
		Network network = networkService.queryByName(name);
		json.put("data", network);
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Network network = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Network.class);
		String name = network.getName();
		String description = network.getDescription();
		int type = network.getType();
		int code = 0;
		String msg = "新建成功";
		
		try {
			if(!RegexUtil.check(RegexUtil.NETWORK_NAME_REGEX, name)) {
				throw new RuntimeException("网络名称格式错误");
			}
			if(StringUtil.isNotBlank(description) && description.length() > 50) {
				throw new RuntimeException("网络描述内容过长");
			}
			if(!(type == 0 || type == 1)) {
				throw new RuntimeException("网络类型错误");
			}
			Network tmp = networkService.queryByName(name);
			if(tmp != null) {
				throw new RuntimeException("网络名称已存在");
			}
			networkService.insert(network);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		
		int networkId = network.getId();
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("networkId", networkId);
		json.put("code", code);
		return json.toJSONString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(String jsonStr) {
		Network network = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Network.class);
		String name = network.getName();
		String description = network.getDescription();
		int type = network.getType();
		int code = 0;
		String msg = "修改成功";
		int networkId = network.getId();
		try {
			if(networkId <= 0) {
				throw new RuntimeException("网络ID格式错误");
			}
			if(!RegexUtil.check(RegexUtil.NETWORK_NAME_REGEX, name)) {
				throw new RuntimeException("网络名称格式错误");
			}
			if(StringUtil.isNotBlank(description) && description.length() > 50) {
				throw new RuntimeException("网络描述内容过长");
			}
			if(!(type == 0 || type == 1)) {
				throw new RuntimeException("网络类型错误");
			}
			Network tmp = networkService.queryByName(name);
			if(!(tmp == null || tmp.getId() == networkId)) {
				throw new RuntimeException("网络名称已存在");
			}
			networkService.update(network);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toJSONString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int networkId) {
		int code = 0;
		String msg = "删除成功";
		try {
			if(networkId <= 0) {
				throw new RuntimeException("网络ID信息错误");
			}
			Network network = networkService.queryById(networkId);
			if(network == null) {
				throw new RuntimeException("该网络信息不存在");
			}
			networkService.delete(networkId);
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
