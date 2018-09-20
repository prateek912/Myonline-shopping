package com.spring.backend.daoimpl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.dao.ProductDao;
import com.spring.backend.dto.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SessionFactory factory;
	
	@Override
	public Product getProductById(int id) {
		try {
			return (Product) factory.getCurrentSession().get(Product.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		try {
			return factory.getCurrentSession().
					createQuery("FROM Product",Product.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			factory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			factory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Product product) {
		// We are not deleting product, we are just making it de-active
		product.setActive(false);
		try {
			// Call Update method of this class 
			return this.updateProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Product> getListOfActiveProducts() {
		String selectActiveProducts = "FROM Product where active =:active";
		return factory.getCurrentSession()
									.createQuery(selectActiveProducts,Product.class)
										.setParameter("active",true)
											.getResultList();
	}

	@Override
	public List<Product> getListOfActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product where active =:active and categoryid =:categoryId";
		return factory.getCurrentSession()
									.createQuery(selectActiveProductsByCategory,Product.class)
										.setParameter("active",true)
											.setParameter("categoryId",categoryId)
											.getResultList();
	}

	@Override
	public List<Product> getActiveLastestProducts(int count) {
		String selectActiveLatestProducts = "FROM Product where active =:active order by id";
		return factory.getCurrentSession()
									.createQuery(selectActiveLatestProducts,Product.class)
										.setParameter("active",true)
											.setFirstResult(0)
												.setMaxResults(count)
													.getResultList();
	}

}
