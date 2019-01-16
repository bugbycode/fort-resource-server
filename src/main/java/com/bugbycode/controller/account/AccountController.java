package com.bugbycode.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.account.Account;
import com.bugbycode.module.resource.Resource;
import com.bugbycode.service.account.AccountService;
import com.bugbycode.service.resource.ResourceService;
import com.util.AESUtil;
import com.util.StringUtil;
import com.util.reg.RegexUtil;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(int resId) {
		JSONObject json = new JSONObject();
		json.put("data", accountService.query(resId));
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Account acc = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Account.class);
		int resId = acc.getResourceId();
		String account = acc.getAccount();
		String password = acc.getPassword();
		int type = acc.getType();
		int code = 0;
		String msg = "新建成功";
		try {
			if(resId <= 0) {
				throw new RuntimeException("资产ID错误");
			}
			Resource r = resourceService.queryById(resId);
			if(r == null) {
				throw new RuntimeException("资产ID错误");
			}
			if(StringUtil.isNotBlank(password)) {
				password = AESUtil.encrypt(password);
				acc.setPassword(password);
			}
			if(!RegexUtil.check(RegexUtil.ACCOUNT_REGEX, account)) {
				throw new RuntimeException("账号格式错误");
			}
			if(account.length() > 50) {
				throw new RuntimeException("账号格式错误");
			}
			if(!(type == 0 || type == 1)) {
				throw new RuntimeException("账号类型错误");
			}
			
			List<Account> list = accountService.query(resId);
			Account tmp = accountService.checkAccount(list, acc);
			if(tmp != null) {
				throw new RuntimeException("该账号已存在");
			}
			accountService.insert(acc);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		int accId = acc.getId();
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("accId", accId);
		json.put("code", code);
		return json.toJSONString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(String jsonStr) {
		Account acc = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Account.class);
		int accId = acc.getId();
		int resId = acc.getResourceId();
		String account = acc.getAccount();
		String password = acc.getPassword();
		int type = acc.getType();
		int code = 0;
		String msg = "修改成功";
		try {
			if(accId <= 0) {
				throw new RuntimeException("账号ID错误");
			}
			List<Account> list = accountService.query(resId);
			Account tmp = accountService.findById(list, accId);
			if(tmp == null) {
				throw new RuntimeException("账号ID错误");
			}
			if(resId <= 0) {
				throw new RuntimeException("资产ID错误");
			}
			Resource r = resourceService.queryById(resId);
			if(r == null) {
				throw new RuntimeException("资产ID错误");
			}
			if(StringUtil.isNotBlank(password)) {
				password = AESUtil.encrypt(password);
				acc.setPassword(password);
			}
			if(!RegexUtil.check(RegexUtil.ACCOUNT_REGEX, account)) {
				throw new RuntimeException("账号格式错误");
			}
			if(account.length() > 50) {
				throw new RuntimeException("账号格式错误");
			}
			if(!(type == 0 || type == 1)) {
				throw new RuntimeException("账号类型错误");
			}

			acc.setResourceId(tmp.getResourceId());
			acc.setServerType(tmp.getServerType());
			
			tmp.copy(acc);
			
			acc = accountService.checkAccount(list, tmp);
			if(!(acc == null || acc.getId() == tmp.getId())) {
				throw new RuntimeException("账号名称重复");
			}
			accountService.update(tmp);
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
	public String delete(int accId,int resId) {
		int code = 0;
		String msg = "删除成功";
		try {
			if(accId <= 0) {
				throw new RuntimeException("账号ID信息错误");
			}
			List<Account> list = accountService.query(resId);
			Account acc = accountService.findById(list, accId);
			if(acc == null) {
				throw new RuntimeException("该账号信息不存在");
			}
			accountService.delete(accId);
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
