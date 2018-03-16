package com.concretepage.rating.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.BaseDAO;


@Transactional
@Repository
public class RatingDAO extends BaseDAO<Rating> implements IRatingDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	private Class<Rating> type;
	
	@SuppressWarnings("unchecked")
	public RatingDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<Rating>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public Rating getRatingByIpandId(Long id, String ip) {
		try{
			return  entityManager.createQuery("select a FROM "+type.getName()+" a WHERE a.id = :id and a.ip_address = :ip", Rating.class)
					.setParameter("ip", ip)
					.setParameter("id", id)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
		
	}	
	
}
