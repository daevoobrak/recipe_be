package com.concretepage.category.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.category.entity.Item;
import com.concretepage.recipe.dao.BaseDAO;


@Transactional
@Repository
public class ItemDAO extends BaseDAO<Item> implements IItemDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	private Class<Item> type;
	
	@SuppressWarnings("unchecked")
	public ItemDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<Item>) pt.getActualTypeArguments()[0];
	}
	

	@Override
	public Item findProduct(String category, String subCategory, String item) {
		try{
			return  entityManager.createQuery("select a from "+type.getName()+" a inner join a.subcategory s inner join a.subcategory.category c where c.name = :category and s.name = :subCategory and a.name = :item ", Item.class)
					.setParameter("category", category)
					.setParameter("subCategory", subCategory)
					.setParameter("item", item)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}	
	
}
