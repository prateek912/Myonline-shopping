package com.spring.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.dao.UserDao;
import com.spring.backend.dto.Address;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{

	// For logging
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public boolean addUser(User user) {
		try {
			factory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			logger.error("Exception Occured while adding user!!",e);
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			factory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			logger.error("Exception Occured while adding User Address!!",e);
			return false;
		}
	}

	@Override
	public boolean udpateCart(Cart cart) {
		try {
			factory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			logger.error("Exception Occured while updating User Cart!!",e);
			return false;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		String str = "from User where email =:email";
		try {
			User user =factory.getCurrentSession().createQuery(str, User.class)
					.setParameter("email",email)
						.getSingleResult();
			return user;
		} catch (Exception e) {
			logger.error("Exception while Fetching user by email!!",e);
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String str = "from Address where user =:user AND billing =:billing";
		try {
			return factory.getCurrentSession().createQuery(str,Address.class)
				.setParameter("user",user)
					.setParameter("billing", true)
						.getSingleResult();
		} catch (Exception e) {
			logger.error("Exception while fetching billing address of User !!",e);
			return null;
		}
	}

	@Override
	public List<Address> getListOfShippingAddress(User user) {
		String str = "from Address where user =:user AND shipping =:shipping";
		try {
			return factory.getCurrentSession().createQuery(str,Address.class)
				.setParameter("user",user)
					.setParameter("shipping", true)
						.getResultList();
		} catch (Exception e) {
			logger.error("Exception while fetching shipping address of User !!",e);
			return null;
		}
	}

	

}
