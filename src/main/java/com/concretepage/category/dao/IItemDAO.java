package com.concretepage.category.dao;
import com.concretepage.category.entity.Category;
import com.concretepage.category.entity.Item;
import com.concretepage.recipe.dao.IBaseDAO;

public interface IItemDAO extends IBaseDAO<Item> {

	Item findProduct(String category, String subCategory, String item);
    
}
 