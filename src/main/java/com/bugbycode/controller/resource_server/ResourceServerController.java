package com.bugbycode.controller.resource_server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.resource.Resource;
import com.bugbycode.module.resource_server.ResourceServer;
import com.bugbycode.service.resource.ResourceService;
import com.bugbycode.service.resource_server.ResourceServerService;

@Controller
@RequestMapping("/resourceServer")
public class ResourceServerController {
	
	@Autowired
	private ResourceServerService resourceServerService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(int resId) {
		List<ResourceServer> list = resourceServerService.query(resId);
		JSONObject json = new JSONObject();
		json.put("data", list);
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		ResourceServer server = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), ResourceServer.class);
		int code = 0;
		String msg = "新建成功";
		int resId = server.getResId();
		try {
			if(resId <= 0) {
				throw new RuntimeException("资源ID错误");
			}
			Resource res = resourceService.queryById(resId);
			if(res == null) {
				throw new RuntimeException("资源ID错误");
			}
			
			ResourceServer tmp = resourceServerService.query(resId, server.getServerType());
			if(tmp == null) {
				resourceServerService.insert(server);
			}else {
				server.setId(tmp.getId());
				resourceServerService.update(server);
			}
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		json.put("serverId", server.getId());
		return json.toJSONString();
	}
}
