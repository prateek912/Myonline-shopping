package com.spring.backend.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dto.Category;

@Repository("catDao")
public class CategoryDaoImpl implements CategoryDao{

	static List<Category> categories =  new ArrayList<>();
	static {
		Category cat1 = new Category();
		cat1.setId(1);
		cat1.setName("Television");
		cat1.setDescription("For watching!");
		cat1.setActive(true);
		cat1.setImgUrl("cat1.png");
		
		Category cat2 = new Category();
		cat2.setId(2);
		cat2.setName("XBox");
		cat2.setDescription("For Gaming!");
		cat2.setActive(true);
		cat2.setImgUrl("cat2.png");
		
		Category cat3 = new Category();
		cat3.setId(3);
		cat3.setName("Mobile");
		cat3.setDescription("For Everything!");
		cat3.setActive(true);
		cat3.setImgUrl("cat3.png");
	
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		
	}
	
	@Override
	public List<Category> getList() {
		return categories;
	}

	@Override
	public Category getById(int id) {
		for(Category cat : categories) {
			if(cat.getId() == id) {
				return cat;
			}
		}
		
		// If nothing matches
		return null;
	}

}
