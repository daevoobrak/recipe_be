package com.concretepage.recipe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.recipe.dao.IDetailDAO;
import com.concretepage.recipe.dao.IRecipeDAO;
import com.concretepage.recipe.entity.Detail;
import com.concretepage.recipe.entity.Recipe;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class RecipeService implements IRecipeService {
	JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	@Autowired
	private IRecipeDAO recipeDAO;
	
	@Autowired
	private IDetailDAO detailDAO;
	
	@Override
	public Recipe getRecipeById(Long recipeId) {
		Recipe obj = recipeDAO.getRecipeById(recipeId);
		return obj;
	}	
	@Override
	public List getAllRecipes(){
		List<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList = recipeDAO.getAllRecipes();
		List recipeObjList = new ArrayList();
		Detail det = new Detail();
		for (Recipe recipe : recipeList) {
			ObjectNode obj = nodeFactory.objectNode();
			det = recipe.getDetail();
			obj.put("id", recipe.getId());
			obj.put("name", recipe.getName());
			obj.put("category", recipe.getCategory());
			obj.put("description", det.getDescription());
			obj.put("author", det.getAuthor());
			obj.put("rating", det.getRating());
			recipeObjList.add(obj);
		}
		return recipeObjList;
	}
	@Override
	public synchronized boolean createRecipe(ObjectNode recipe){
       if (recipeDAO.recipeExists(recipe.get("name").asText(), recipe.get("category").asText())) {
    	   return false;
       } else {
    	   Recipe rec = new Recipe();
    	   Detail detail = new Detail();
    	   detail.setDescription(recipe.get("description").asText());
    	   detailDAO.create(detail);
    	   rec.setDetail(detail);
    	   rec.setCategory(recipe.get("category").asText());
    	   rec.setName(recipe.get("name").asText());
    	   recipeDAO.create(rec);
    	   return true;
       }
	}
	@Override
	public void updateRecipe(Recipe recipe) {
		recipeDAO.updateRecipe(recipe);
	}
	@Override
	public void deleteRecipe(Long recipeId) {
		recipeDAO.deleteRecipe(recipeId);
	}
}
