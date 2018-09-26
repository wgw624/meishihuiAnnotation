package com.meishihui.dao.impl;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meishihui.dao.UserDao;
import com.meishihui.enties.User;

@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImp<User> implements UserDao {

}
