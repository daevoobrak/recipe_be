package com.concretepage.recipe.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concretepage.recipe.entity.Category;
import com.concretepage.recipe.entity.Recipe;
import com.concretepage.recipe.service.IRecipeService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Controller
@RequestMapping("recipe")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RecipeController {
	@Autowired
	private IRecipeService recipeService;
	@GetMapping("recipe")
	public ResponseEntity<Recipe> getrecipeById(@RequestParam("id") String id) {
		Recipe recipe = recipeService.getRecipeById(Long.parseLong(id));
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
	}
	@GetMapping("all-recipes")
	public ResponseEntity<List> getAllrecipes() {
		List list = recipeService.getAllRecipes();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}
	
	@GetMapping("category")
	public ResponseEntity<List> getCategory() {
		List categoryList =
                new ArrayList(EnumSet.allOf(Category.class));
		return new ResponseEntity<List>(categoryList, HttpStatus.OK);
	}
	@PostMapping("create")
	public ResponseEntity<ObjectNode> createRecipe(@RequestBody ObjectNode recipe) {
		JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
		ObjectNode response = nodeFactory.objectNode();
		boolean flag = recipeService.createRecipe(recipe);
        if (flag == false) {
        	response.put("error", "Error while creating recipe");
        	return new ResponseEntity<ObjectNode>(response,HttpStatus.CONFLICT);
        } else {
        	response.put("message", "Recipe created successfully");
        	return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
        }
	}
	@PutMapping("update")
	public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
		recipeService.updateRecipe(recipe);
		return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
	}
	@DeleteMapping("remove")
	public ResponseEntity<Void> deleteRecipe(@RequestParam("id") String id) {
		recipeService.deleteRecipe(Long.parseLong(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
