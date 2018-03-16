package com.concretepage.recipe.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.recipe.entity.Recipe;


@Transactional
@Repository
public class RecipeDAO extends BaseDAO<Recipe> implements IRecipeDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Recipe getRecipeById(Long RecipeId) {
		return entityManager.find(Recipe.class, RecipeId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getAllRecipes() {
		String hql = "FROM Recipe as atcl ORDER BY atcl.id DESC";
		return (List<Recipe>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createRecipe(Recipe Recipe) {
		entityManager.persist(Recipe);
	}
	@Override
	public void updateRecipe(Recipe Recipe) {
		Recipe recipe = getRecipeById(Recipe.getId());
		//recipe.setDescription(Recipe.getDescription());
		//recipe.setCategory(Recipe.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteRecipe(Long RecipeId) {
		entityManager.remove(getRecipeById(RecipeId));
	}
	@Override
	public boolean recipeExists(String title, String category) {
		String hql = "FROM Recipe as atcl WHERE atcl.name = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
