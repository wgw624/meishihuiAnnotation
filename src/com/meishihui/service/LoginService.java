package com.meishihui.service;

import java.util.List;

import com.meishihui.enties.User;

public interface LoginService {
	User loginSys(User user);
	void updateUser(User user);
	List<User> getAllUser();
}
