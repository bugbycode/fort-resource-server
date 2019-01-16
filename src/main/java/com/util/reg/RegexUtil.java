package com.util.reg;

import org.apache.commons.validator.routines.RegexValidator;

public class RegexUtil {
	
	public final static String USER_LOGIN_NAME_REGEX = "^[A-Za-z0-9_]+$";
	
	public final static String USER_NAME_REGEX = "^[A-Za-z0-9_\\u4e00-\\u9fa5]+$";
	
	public final static String RESOURCE_NAME_REGEX = "^[A-Za-z0-9_\\\\u4e00-\\\\u9fa5]+$";
	
	public final static String ROLE_NAME_REGEX = "^[A-Za-z0-9_\\u4e00-\\u9fa5]+$";
	
	public final static String USER_GROUP_NAME_REGEX = "^[A-Za-z0-9_\\u4e00-\\u9fa5]+$";
	
	public final static String EMAIL_REGEX = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
	
	public final static String ACCOUNT_REGEX = "^[A-Za-z0-9_\\@\\.]+$";
	
	public static boolean check(String regex,String input) {
		RegexValidator validator = new RegexValidator(regex);
		return validator.isValid(input);
	}
	
	public static void main(String[] args) {
		System.out.println(check(ACCOUNT_REGEX, "abc_dd#"));
	}
}
