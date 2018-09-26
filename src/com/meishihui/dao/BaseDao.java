package com.meishihui.dao;

import java.util.List;

public interface BaseDao<T> {
	void addEntity(T t);
	void deleteEntity(T t);
	void updateEntity(T t);
	void saveOrUpdate(T t);
	List<T> getAllEntity();
	List<T> getEntityByHQL(String hql,Object...objects);
	//·µ»Øµ¥Öµ
	Object uniqueResult(String hql,Object...objects);
	
	T layLoadUniqueResult(String id);
}
