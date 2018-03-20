package com.concretepage.category.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.category.entity.Category;
import com.concretepage.category.entity.Item;
import com.concretepage.category.entity.SubCategory;
import com.concretepage.recipe.dao.BaseDAO;


@Transactional
@Repository
public class SubCategoryDAO extends BaseDAO<SubCategory> implements ISubCategoryDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	private Class<SubCategory> type;
	
	@SuppressWarnings("unchecked")
	public SubCategoryDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<SubCategory>) pt.getActualTypeArguments()[0];
	}
	

	@Override
	public SubCategory findProduct(String category, String subCategory) {
		try{
			return  entityManager.createQuery("select a from "+type.getName()+" a inner join a.category c where c.name = :category and a.name = :subCategory ", SubCategory.class)
					.setParameter("category", category)
					.setParameter("subCategory", subCategory)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}	
	
}
