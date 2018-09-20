package com.spring.backend.dao;

import java.util.List;
import com.spring.backend.dto.Category;

public interface CategoryDao {

	public List<Category> getList();
	public Category getById(int id);
	public boolean addCategory(Category cat);
	public boolean updateCategory(Category cat);
	public boolean deleteCategory(Category cat);
	
}
