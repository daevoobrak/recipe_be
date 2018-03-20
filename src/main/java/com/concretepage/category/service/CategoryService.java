package com.concretepage.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.category.dao.ICategoryDAO;
import com.concretepage.category.dao.IItemDAO;
import com.concretepage.category.dao.ISubCategoryDAO;
import com.concretepage.category.entity.Category;
import com.concretepage.category.entity.Item;
import com.concretepage.category.entity.SubCategory;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CategoryService implements ICategoryService {
	JsonNodeFactory nodeFactory = new JsonNodeFactory(true);
	@Autowired
	private IItemDAO itemDAO;
	@Autowired
	private ISubCategoryDAO subCatDao;
	
	@Autowired
	private ICategoryDAO catDao;
	
	

	@Override
	public boolean addCategory(ObjectNode obj, ObjectNode response) {
		try{
			String category = obj.get("category").asText();
			String subCategory = obj.get("subCategory").asText();
			String item = obj.get("item").asText();
			Item it = itemDAO.findProduct(category, subCategory, item);
			if(null == it){
				SubCategory scat = subCatDao.findProduct(category, subCategory);
				if(null == scat){
					Category cat =  catDao.findProduct(category);
					if(null == cat) {
						Category nCat = new Category();
						SubCategory nSub = new SubCategory();
						Item nItem = new Item();
						nItem.setName(item);
						nItem.setDescription("Desc: "+item);
						itemDAO.create(nItem);
						
						nSub.addItem(nItem);
						nSub.setName(subCategory);
						nSub.setDescription("Desc: "+subCategory);
						subCatDao.create(nSub);
						
						nCat.addSubCategory(nSub);
						nCat.setName(category);
						nCat.setDescription("Desc : "+ category);
						catDao.create(nCat);
					} else {
						SubCategory nSub = new SubCategory();
						Item nItem = new Item();
						nItem.setName(item);
						nItem.setDescription("Desc: "+item);
						itemDAO.create(nItem);
						
						nSub.addItem(nItem);
						nSub.setName(subCategory);
						nSub.setDescription("Desc: "+subCategory);
						subCatDao.create(nSub);
						
						cat.addSubCategory(nSub);
						catDao.update(cat);
					}
				} else {
					Item nItem = new Item();
					nItem.setName(item);
					nItem.setDescription("Desc: "+item);
					itemDAO.create(nItem);
					
					scat.addItem(nItem);
					subCatDao.update(scat);
				}
			} else {
				response.put("response", "Item Already exists");
			}
			return true;
		} catch (Exception e){
			return false;	
		}
	}
	
	
}
