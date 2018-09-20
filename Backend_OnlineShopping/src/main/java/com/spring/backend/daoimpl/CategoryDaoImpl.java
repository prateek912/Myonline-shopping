package com.spring.backend.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spring.backend.dao.CategoryDao;
import com.spring.backend.dto.Category;

@Repository("catDao")
//As we have @EnableTransactionalManager in Hibernate Configuration
@Transactional
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	private SessionFactory factory;
	
	@Override
	public List<Category> getList() {
		// Writing HQL for selecting only active categories
		String selectActiveCategories = "FROM Category where active =:active";
		Query<Category> query = factory.getCurrentSession().createQuery(selectActiveCategories,Category.class);
		query.setParameter("active",true);
	
		return query.getResultList();
	}

	@Override
	public Category getById(int id) {
		// For getting single Category based on Id
		return factory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}

	@Override
	public boolean addCategory(Category cat) {
		try {
			// Adding Category
			Session session =factory.getCurrentSession();
			session.persist(cat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCategory(Category cat) {
		try {
			factory.getCurrentSession().update(cat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCategory(Category cat) {
		// We are not deleting the Category, we are just setting active status as false
		cat.setActive(false);
		try {
			factory.getCurrentSession().update(cat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
