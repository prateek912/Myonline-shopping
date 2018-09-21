package com.spring.backend.test;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Product;

public class ProductTest {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDao productDao;
	private static Product product;
	
	@BeforeClass
	public static void inti() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.backend");
		context.refresh();
		
		productDao = context.getBean(ProductDao.class);
	}

	// Testing all CRUD Operation at Once
	@Test
	@Ignore
	public void testProductCRUDOperation() {
		// For adding
		product = new Product();
		product.setName("Logitech Mouse");
		product.setBrand("Logitect");
		product.setDescription("Mouse for Tracking screen purpose!!");
		product.setPurchases(1);
		product.setQuantity(10);
		product.setUnitprice(100.00);
		product.setViews(1);
		product.setCategoryid(3);
		product.setSupplierId(3);	
		
		assertEquals("Successfully Added!!",true,productDao.addProduct(product));
		
		
		// For fetching and updating product
		Product fetchProduct = productDao.getProductById(8);
		fetchProduct.setBrand("Logitech");
		
		assertEquals("Something went wrong while Fetched and Updated!!",true,productDao.updateProduct(fetchProduct));
		
		// For Deleting or De-activating Product
		assertEquals("Something went wrong while De-Activated!!",true,productDao.deleteProduct(fetchProduct));
		
		// Get All Products
		assertEquals("Something went wrong while Fetching All Products",6,productDao.getAllProducts().size());
		
		// Get All Active Products
		assertEquals("Something went wrong while Fetching All Active Products",5,
					productDao.getListOfActiveProducts().size());
		
		// Get All active Product of Particular Category
		assertEquals("Something went wrong while Fetching All Active Products Of Category",3,
				productDao.getListOfActiveProductsByCategory(3).size());
	}
	
	// Testing Business Specific Methods
	@Test
	@Ignore
	public void testListOfActiveProduct() {
		// Get All Active Products
		assertEquals("Something went wrong while Fetching All Active Products",5,
					productDao.getListOfActiveProducts().size());
	}
	
	@Test
	@Ignore
	public void testListOfActiveProductByCategory() {
		// Get All Active Products by category Id
				assertEquals("Something went wrong while Fetching All Active Products Of Category",2,
							productDao.getListOfActiveProductsByCategory(1).size());
	}
	
	@Test
	@Ignore
	public void testActiveLastestProducts() {
		// Get All Active Products by category Id
				assertEquals("Something went wrong while Fetching All Active Latest Products",3,
							productDao.getActiveLastestProducts(3).size());
	}
	
}
