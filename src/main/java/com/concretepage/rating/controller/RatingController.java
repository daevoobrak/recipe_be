package com.concretepage.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.rating.service.IRatingService;
import com.concretepage.recipe.service.IRecipeService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Controller
@RequestMapping("rating")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RatingController {
	@Autowired
	private IRecipeService recipeService;
	@Autowired
	private IRatingService ratingService;
	
	@PostMapping("rate")
	public ResponseEntity<ObjectNode> createRecipe(@RequestBody ObjectNode rating) {
		JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
		ObjectNode response = nodeFactory.objectNode();
		boolean flag = ratingService.rate(rating, response);
        if (flag == false) {
        	response.put("error", "Error while creating recipe");
        	return new ResponseEntity<ObjectNode>(response,HttpStatus.CONFLICT);
        } else {
        	response.put("message", "Recipe created successfully");
        	return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
        }
	}
}
