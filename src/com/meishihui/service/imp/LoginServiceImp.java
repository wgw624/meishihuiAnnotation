package com.meishihui.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meishihui.dao.UserDao;
import com.meishihui.enties.User;
import com.meishihui.service.LoginService;
import com.meishihui.util.CommonUtil;
import com.meishihui.util.MD5;

@Service(value="loginService")
public class LoginServiceImp implements LoginService {
	@Resource(name="userDao")
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User loginSys(User user) {
		String userName = user.getLoginName();
		String password = user.getPassword();
		User loginUser = null;
		try {
			password = MD5.getMd5Str(password);
			System.out.println(password);
			String hql = "FROM User where loginName = ? and password = ?";
			Object []objects = new Object[]{userName,password};
			loginUser = (User) userDao.uniqueResult(hql, objects);
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return loginUser;
	}
	@Override
	public List<User> getAllUser() {
		List<User> listUser = userDao.getAllEntity();
		return listUser;
	}
	@Override
	public void updateUser(User user) {
		userDao.updateEntity(user);
		
	}

}
