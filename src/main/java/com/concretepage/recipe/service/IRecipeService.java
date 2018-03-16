package com.concretepage.recipe.service;

import java.util.List;

import com.concretepage.recipe.entity.Recipe;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface IRecipeService {
     List getAllRecipes();
     Recipe getRecipeById(Long recipeId);
     boolean createRecipe(ObjectNode recipe);
     void updateRecipe(Recipe recipe);
     void deleteRecipe(Long recipeId);
}
