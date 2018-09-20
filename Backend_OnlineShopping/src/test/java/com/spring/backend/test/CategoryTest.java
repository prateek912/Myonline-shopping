package com.spring.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dto.Category;

public class CategoryTest {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDao catDao;
	private static Category cat;
	
	// To initialize all the above declared variable before test cases run
	@BeforeClass
	public static void inti() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.backend");
		context.refresh();
		
		catDao = (CategoryDao) context.getBean(CategoryDao.class);
	}
	
	@Test
	@Ignore
	public void testAddCategory() {
		cat = new Category();
		cat.setName("Televisions");
		cat.setDescription("For Watching moving pictures!!");
		cat.setImgUrl("tv.png");
		
		assertEquals("Successfully added Category!!!", true, catDao.addCategory(cat));
	}
	
	@Test
	@Ignore
	public void testGetCategory() {
		Category fetchCat = catDao.getById(1);
		// matching name of category
		assertEquals("Successfully Fetched Category!!","Laptops",fetchCat.getName());
	}
	
	@Test
	@Ignore
	public void testUpdateCategory() {
		Category fetchCat = catDao.getById(1);
		fetchCat.setName("PS4");
		// Checking updated category
		assertEquals("Successfully Updated Category!!",true,catDao.updateCategory(fetchCat));
	}
	
	@Test
	@Ignore
	public void testDeleteCategory() {
		Category fetchCat = catDao.getById(1);
		assertEquals("Successfully De-Activated Category!!",true,catDao.deleteCategory(fetchCat));
	}
	
	@Test
	@Ignore
	public void testListCategory() {
		// Comparing number returned results
		assertEquals("Successfully Fetched List of Active Category!!",1,catDao.getList().size());
	}
	
	// Combining all the CRUD method in One
	@Test
	@Ignore
	public void testCRUDOperation() {
		// First Add Operation
		cat = new Category();
		cat.setName("Mobile");
		cat.setDescription("For Gaming!!");
		cat.setImgUrl("mobile.png");
		
		assertEquals("Successfully added Category!!!", true, catDao.addCategory(cat));
		
		cat = new Category();
		cat.setName("Cars");
		cat.setDescription("For Driving!!");
		cat.setImgUrl("car.png");
		
		assertEquals("Successfully added Category!!!", true, catDao.addCategory(cat));
		
		// Fetching and Update Operation
		Category fetchCat = catDao.getById(2);
		fetchCat.setName("Vehicles");
		// Checking updated category
		assertEquals("Successfully Updated Category!!",true,catDao.updateCategory(fetchCat));
		
		// De-activating category
		fetchCat = catDao.getById(2);
		assertEquals("Successfully De-Activated Category!!",true,catDao.deleteCategory(fetchCat));
		
		// Get all category
		assertEquals("Successfully Fetched List of Active Category!!",1,catDao.getList().size());
	}
}
