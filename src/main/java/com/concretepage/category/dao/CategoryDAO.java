package com.concretepage.category.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.category.entity.Category;
import com.concretepage.category.entity.SubCategory;
import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.BaseDAO;


@Transactional
@Repository
public class CategoryDAO extends BaseDAO<Category> implements ICategoryDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	private Class<Category> type;
	
	@SuppressWarnings("unchecked")
	public CategoryDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<Category>) pt.getActualTypeArguments()[0];
	}
	

	@Override
	public Category findProduct(String category) {
		try{
			return  entityManager.createQuery("select a from "+type.getName()+" a where a.name = :category ", Category.class)
					.setParameter("category", category)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}	
	
}
