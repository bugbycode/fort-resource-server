package com.bugbycode.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.account.Account;
import com.bugbycode.module.resource.Resource;
import com.bugbycode.module.resource_server.ResourceServer;
import com.bugbycode.service.account.AccountService;
import com.bugbycode.service.resource.ResourceService;
import com.bugbycode.service.resource_server.ResourceServerService;

@RestController
@RequestMapping("/api")
public class ResourceApiController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ResourceServerService resourceServerService;
	
	@RequestMapping("/getResourceByResId")
	public String getResourceByResId(int resId) {
		Resource r = resourceService.queryById(resId);
		JSONObject json = new JSONObject();
		if(r != null) {
			json.put("data", r);
		}
		return json.toString();
	}
	
	@RequestMapping("/getSsoInfo")
	public String getSsoInfo(int resId,int serverType) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		ResourceServer server = resourceServerService.query(resId, serverType);
		if(server != null) {
			data.put("port", server.getPort());
			data.put("use", server.getUse());
			data.put("database", server.getDbName());
			List<Account> accountList = accountService.query(server.getId());
			if(!CollectionUtils.isEmpty(accountList)) {
				data.put("account", accountList);
			}
		}
		json.put("data", data);
		return json.toString();
	}
}
