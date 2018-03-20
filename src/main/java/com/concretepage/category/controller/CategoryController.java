package com.concretepage.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.category.service.ICategoryService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICategoryService icategoryService;
	
	@PostMapping("add")
	public ResponseEntity<ObjectNode> createRecipe(@RequestBody ObjectNode rating) {
		JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
		ObjectNode response = nodeFactory.objectNode();
		boolean flag = categoryService.addCategory(rating, response);
        if (flag == false) {
        	response.put("error", "Error while creating recipe");
        	return new ResponseEntity<ObjectNode>(response,HttpStatus.CONFLICT);
        } else {
        	response.put("message", "Recipe created successfully");
        	return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
        }
	}
}
