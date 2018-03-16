package com.concretepage.recipe.dao;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.transaction.annotation.Transactional;



@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public abstract class BaseDAO<T> implements IBaseDAO<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> type;
	
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public T create(final T t) {
		this.em.persist(t);
		return t;
	}
	
	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}
	
	@Override
	public T find(final Object id) {
		T obj;
		try {
			obj = (T) this.em.find(type, id);
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}

	@Override
	public List<T> findAll(Class<T> t) {
        return em.createQuery("select c from " + t.getName() +" c", t).getResultList();
	}
	
	@Override
	public int deleteAll(Class<T> t) {
		
		return em.createQuery("delete from "+t.getName()).executeUpdate();
	}
	
	@Override
	public int getCount(Class<T> t) {
		Number result = (Number) em.createQuery("select count(c) from "+t.getName()+" c",Number.class).getSingleResult();
		return result.intValue();
	}
	
}