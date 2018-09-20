package com.spring.backend.dao;

import java.util.List;

import com.spring.backend.dto.Product;

public interface ProductDao {
	
	// CRUD Methods
	public Product getProductById(int id);
	public List<Product> getAllProducts();
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Product product);
	
	// Other method
	public List<Product> getListOfActiveProducts();
	public List<Product> getListOfActiveProductsByCategory(int categoryId);
	public List<Product> getActiveLastestProducts(int count);
}
