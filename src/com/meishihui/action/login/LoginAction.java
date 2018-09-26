package com.meishihui.action.login;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.meishihui.enties.User;
import com.meishihui.service.LoginService;
import com.meishihui.util.CommonUtil;
import com.meishihui.util.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

@Controller(value="loginAction")
@ParentPackage(value="myInterceptor")
@Scope(value="prototype")
/*@Namespace("/login")*/
public class LoginAction extends ActionSupport implements ModelDriven<User> {
	String result;
	
	/*@Resource(name="loginService")*/
	@Autowired
	LoginService loginService;
	
	User user = new User();
	String newPsd;
	
	public void setNewPsd(String newPsd) {
		this.newPsd = newPsd;
	}
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	Map<String,String> dataMap;
	
	public Map<String, String> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("loginSystem()  execute().....");
		dataMap = new HashMap<String,String>();
		dataMap.put("success", "true");
		return "loginSuccess";
		
	}
	public String testLogin() throws Exception {
		return "loginSuccess";
	}
	@Action(value="loginAction_loginSystem",results={@Result(name="loginSuccess",location="/jsp/systemFirstPage.jsp"),
			@Result(name="loginFail",location="/index.jsp")})
	public String loginSystem() throws Exception {
		System.out.println("lalalalalal");
		JSONObject json = new JSONObject();
		json.element("isLogin", "true");
		setResult(json.toString());
		if(!CommonUtil.isNotNullStr(user.getLoginName()) || !CommonUtil.isNotNullStr(user.getPassword())){
			return "loginFail";
		}
		User luser = loginService.loginSys(user);
		if(luser == null){
			return "loginFail";
		}else{
			/*ServletActionContext.getRequest().getSession().setAttribute("user", luser);*/
			ActionContext actionContext = ActionContext.getContext();
			actionContext.getSession().put("user", luser);
			return "loginSuccess";
		}


	}
	
	@Action(value="loginAction_cancel",results={@Result(name="cancel",type="redirectAction",location="/index.jsp")})
	public String cancel() throws Exception{
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getSession().remove("user");
		System.out.println("cancel system");
		return "cancel";
	}
	
	@Action(value="modifyPassword",results={@Result(name="fail",location="/jsp/systemFirstPage.jsp"),
			@Result(name="success",location="/jsp/systemFirstPage.jsp")})
	public String modifyPassword()throws Exception{
		System.out.println("mimaxiugai");
		User suser = (User) ActionContext.getContext().getSession().get("user");
		String psd = user.getPassword();
		suser.setPassword(psd);
		User oldUser = loginService.loginSys(suser);
		if(CommonUtil.isNotNullObj(oldUser)){
			oldUser.setPassword(MD5.getMd5Str(newPsd));
			loginService.updateUser(oldUser);
			return "success";
		}else{
			return "fail";
		}
	}
	@Override
	public User getModel() {
		return user;
	}
}
