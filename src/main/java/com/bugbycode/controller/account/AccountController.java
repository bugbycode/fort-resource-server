package com.bugbycode.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bugbycode.module.account.Account;
import com.bugbycode.service.account.AccountService;
import com.util.AESUtil;
import com.util.StringUtil;
import com.util.reg.RegexUtil;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(int serverId) {
		JSONObject json = new JSONObject();
		json.put("data", accountService.query(serverId));
		return json.toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String jsonStr) {
		Account acc = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Account.class);
		String account = acc.getAccount();
		String password = acc.getPassword();
		int type = acc.getType();
		int code = 0;
		String msg = "新建成功";
		try {
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
		return json.toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(String jsonStr) {
		Account acc = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Account.class);
		int accId = acc.getId();
		String account = acc.getAccount();
		String password = acc.getPassword();
		int type = acc.getType();
		int code = 0;
		String msg = "修改成功";
		try {
			if(accId <= 0) {
				throw new RuntimeException("账号ID错误");
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
			accountService.update(acc);
		}catch (Exception e) {
			code = 1;
			msg = e.getMessage();
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/insertRel")
	@ResponseBody
	public String insertRel(int accId,int serverId) {
		int code = 0;
		String msg = "新建成功";
		try {
			int count = accountService.countRel(accId, serverId);
			if(count == 0) {
				accountService.insertRel(accId, serverId);
			}
		}catch (Exception e) {
			msg = e.getMessage();
			code = 1;
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int accId,int serverId) {
		int code = 0;
		String msg = "删除成功";
		try {
			accountService.deleteRel(accId, serverId);
			Account acc = accountService.queryById(accId);
			if(acc == null) {
				accountService.delete(accId);
			}
		}catch (Exception e) {
			msg = e.getMessage();
			code = 1;
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		json.put("code", code);
		return json.toString();
	}
}
