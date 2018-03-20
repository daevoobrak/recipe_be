package com.concretepage.category.dao;
import com.concretepage.category.entity.Category;
import com.concretepage.rating.entity.Rating;
import com.concretepage.recipe.dao.IBaseDAO;

public interface ICategoryDAO extends IBaseDAO<Category> {

	Category findProduct(String category);
    
}
 