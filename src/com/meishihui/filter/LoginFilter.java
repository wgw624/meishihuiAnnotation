package com.meishihui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meishihui.enties.User;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest rq = (HttpServletRequest) req;
		HttpServletResponse rsp = (HttpServletResponse) resp;
		
		HttpSession session = rq.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println("login filter ...."+rq.getRequestURI());
		if(user == null){
			rsp.sendRedirect("/meishihui/index.jsp");
		}else{
			chain.doFilter(rq, rsp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
