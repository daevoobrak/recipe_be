package com.concretepage.recipe.dao;
import java.util.List;

import com.concretepage.recipe.entity.Recipe;

public interface IRecipeDAO extends IBaseDAO<Recipe> {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long RecipeId);
    void createRecipe(Recipe Recipe);
    void updateRecipe(Recipe Recipe);
    void deleteRecipe(Long RecipeId);
    boolean recipeExists(String title, String category);
}
 