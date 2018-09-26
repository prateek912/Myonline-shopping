package com.spring.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.dao.CartLineDao;
import com.spring.backend.dto.Cart;
import com.spring.backend.dto.CartLine;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDao{

	// For logging
	private static final Logger logger = LoggerFactory.getLogger(CartLineDaoImpl.class);
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public CartLine getCartLineById(int id) {
		return factory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean addCartLine(CartLine cartLine) {
		try {
			factory.getCurrentSession().persist(cartLine);
			return true;
		}catch(Exception e) {
			logger.error("Exception occured while adding cartline!!",e);
			return false;
		}
	}

	@Override
	public boolean updateCartLine(CartLine cartLine) {
		try {
			factory.getCurrentSession().update(cartLine);
			return true;
		}catch(Exception e) {
			logger.error("Exception occured while updating cartline!!",e);
			return false;
		}
	}

	@Override
	public boolean deleteCartLine(CartLine cartLine) {
		try {
			factory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception e) {
			logger.error("Exception occured while deleting cartline!!",e);
			return false;
		}
	}

	@Override
	public List<CartLine> getList(int id) {
		String query = "FROM CartLine where cartId =:cartId";
		return factory.getCurrentSession()
						.createQuery(query,CartLine.class)
							.setParameter("cartId",id)
								.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine where cartId =:cartId and available =:available";
		return factory.getCurrentSession()
						.createQuery(query,CartLine.class)
							.setParameter("cartId",cartId)
								.setParameter("available",true)
									.getResultList();
	}

	@Override
	public CartLine getCartLineByProductIdAndCart(int cartId, int productId) {
		String query = "FROM CartLine where cartId =:cartId and product.id =:productId";
		try {
			return factory.getCurrentSession()
					.createQuery(query,CartLine.class)
						.setParameter("cartId",cartId)
							.setParameter("productId",productId)
								.getSingleResult();
		} catch (Exception e) {
			logger.error("Exception while fetching single result of Cart Line !!",e);
			return null;
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
}
