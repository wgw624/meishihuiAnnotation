package com.meishihui.action.sysmenu;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.User;
import com.meishihui.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@ParentPackage(value="myInterceptor")
@Scope(value="prototype")
@Namespace(value="/sysMenu")
@Results({@Result(name="login",location="/jsp/systemFirstPage.jsp")})
public class SystemMenuAction extends ActionSupport {
	
	@Autowired
	private LoginService loginService;

	@Override
	public String execute() throws Exception {
		
		return "success";
	}
	/**
	 * 打开订餐系统页面
	 * @return
	 * @throws Exception
	 */
	@Action(value="SystemMenuAction_openOrderMenu",results={@Result(name="success",location="/jsp/orderMenu/orderMenuMain.jsp")})
	public String openOrderMenu()throws Exception{	
		return "success";
	}
	
	@Action(value="queryUser",results={@Result(name="queryUser",location="/jsp/userManage.jsp")})
	public String queryUser()throws Exception{
		 List<User> listUser = loginService.getAllUser();
		 ActionContext.getContext().put("listUser", listUser);
		 System.out.println(listUser.size()+"....");
		return "queryUser";
	}
}
