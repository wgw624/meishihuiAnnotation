package com.meishihui.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meishihui.dao.BaseDao;

@Transactional
public  class BaseDaoImp<T> implements BaseDao<T> {
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	private Class<T> clazz;
	public BaseDaoImp(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void addEntity(T t) {
		sf.getCurrentSession().save(t);
		
	}

	@Override
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
		
	}

	@Override
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
		
	}

	@Override
	public List<T> getAllEntity() {
		String hql = "FROM "+clazz.getName();
		Query query = sf.getCurrentSession().createQuery(hql);
		List<T> list = query.list();
		return list;
	}

	@Override
	public List<T> getEntityByHQL(String hql, Object... objects) {
		Query query = sf.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		Query query = sf.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}
	@Override
	public T layLoadUniqueResult(String id) {
		Session session = sf.getCurrentSession();
		T t = (T) session.load(clazz,id);
		return t;
	}
	@Override
	public void saveOrUpdate(T t) {
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(t);
	}

}
