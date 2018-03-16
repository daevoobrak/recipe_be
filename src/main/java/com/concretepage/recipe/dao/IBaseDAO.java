package com.concretepage.recipe.dao;

import java.util.List;

public interface IBaseDAO<T> {
	
	public T create(T t);
	
	public T update(T t);
	
	public T find(Object id);
	
	public List<T> findAll(Class<T> t);
	
	public int deleteAll(Class<T> t);

	int getCount(Class<T> t);

}