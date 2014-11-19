package edu.luc.comp433.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.model.Order;

public class OrderDaoImpl extends BaseDaoImpl<Short, Order> implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrderByCustomerLogin(String login) {
		String query = "SELECT customer.orderList FROM Customer "
				+ "customer WHERE customer.login = :login";
		try {
			return getEntityManager().createQuery(query)
					.setParameter("login", login).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

}
