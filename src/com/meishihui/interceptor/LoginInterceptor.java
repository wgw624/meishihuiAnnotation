package com.meishihui.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		Object user = session.get("user");
		if(user == null){
			System.out.println("interceptor....no login");
			return "login";
		}else{
			System.out.println("interceptor....login");
			return invocation.invoke();
		}
	}


}
