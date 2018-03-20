package com.concretepage.category.dao;
import com.concretepage.category.entity.Category;
import com.concretepage.category.entity.SubCategory;
import com.concretepage.recipe.dao.IBaseDAO;

public interface ISubCategoryDAO extends IBaseDAO<SubCategory> {

	SubCategory findProduct(String category, String subCategory);
    
}
 