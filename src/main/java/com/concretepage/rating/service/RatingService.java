package com.concretepage.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.rating.dao.IRatingDAO;
import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.IRecipeDAO;
import com.concretepage.recipe.entity.Detail;
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
	public boolean rate(ObjectNode obj, ObjectNode response) {
		try{
			Long id = obj.get("id").asLong();
			String rating = obj.get("rating").asText();
			String ip = obj.get("ip").asText();
			Rating r = ratingDAO.getRatingByIpandId(id,ip);
			Recipe recipe = new Recipe();
			recipe = irecipeDao.find(id);
			if(null != r){
				r.setRating(rating);
				ratingDAO.update(r);
			} else {
				
				Rating rate = new Rating();
				rate.setIp_address(ip);
				rate.setRating(rating);
				recipe.addRating(rate);
				ratingDAO.create(rate);
				irecipeDao.update(recipe);
			}
			ObjectNode resObj = nodeFactory.objectNode();
			Detail det = recipe.getDetail();
			resObj.put("id", recipe.getId());
			resObj.put("name", recipe.getName());
			resObj.put("category", recipe.getCategory());
			resObj.put("description", det.getDescription());
			resObj.put("author", det.getAuthor());
			//resObj.put("rating", recipe.getRatings().size());
			if(null != recipe.getRatings()){
				List<Rating> rlist = recipe.getRatings();
				int sum = 0;
				for(Rating ra: rlist){
					sum = sum + Integer.parseInt(ra.getRating());
				}
				resObj.put("rating", sum/rlist.size()+"");
			} else {
				resObj.put("rating", "0");
			}
			response.set("recipe", resObj);
			return true;
		} catch (Exception e){
			return false;	
		}
	}
	
	
}
