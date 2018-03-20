package com.concretepage.category.service;

import java.util.List;

import com.concretepage.recipe.entity.Recipe;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ICategoryService {

	boolean addCategory(ObjectNode category, ObjectNode response);
}
