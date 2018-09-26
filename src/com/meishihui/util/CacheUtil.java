package com.meishihui.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meishihui.enties.User;
import com.meishihui.service.LoginService;
import com.meishihui.service.imp.LoginServiceImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CacheUtil extends ActionSupport{
	private LoginService loginService ;
	private static Map<String,String> userMap = new HashMap<String,String>();
	private static Map<String,String> expenseTypeMap = new HashMap<String,String>();
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public void init(){
		initExpenseTypeMap();
		List<User> userList = loginService.getAllUser();
		for(User user:userList){
			userMap.put(user.getLoginName(), user.getShowName());
		}
	}

	public static User getUser(){
		User user = null;
		Map sessionMap = ActionContext.getContext().getSession();
		user = (User) sessionMap.get("user");
		return user;
	}
	public static String getExpenseType(String type){
		return expenseTypeMap.get(type);
	}
	private void initExpenseTypeMap(){
		userMap.put("0", "买菜");
		userMap.put("1", "水费");
		userMap.put("2", "电费");
		userMap.put("3", "燃气费");
		userMap.put("4", "其它");
		userMap.put("5", "酒水饮料");
	}
	/**
	 * 根据登录名获取用户显示名
	 * @param loginName
	 * @return
	 */
	public static String getUserName(String loginName){
		return userMap.get(loginName);
	}
}
