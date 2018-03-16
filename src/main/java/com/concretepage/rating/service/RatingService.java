package com.concretepage.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.rating.dao.IRatingDAO;
import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.IRecipeDAO;
import com.concretepage.recipe.entity.Recipe;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class RatingService implements IRatingService {
	JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	@Autowired
	private IRatingDAO ratingDAO;
	
	@Autowired
	private IRecipeDAO irecipeDao;

	@Override
	public boolean rate(ObjectNode recipe, ObjectNode response) {
		Long id = recipe.get("id").asLong();
		String rating = recipe.get("rating").asText();
		String ip = recipe.get("ip").asText();
		Rating r = ratingDAO.getRatingByIpandId(id,ip);
		if(null != r){
			r.setRating(rating);
		} else {
			Recipe rec = new Recipe();
			rec = irecipeDao.find(id);
			Rating rate = new Rating();
			//rec.set
		}
		return false;
	}
	
	
}
