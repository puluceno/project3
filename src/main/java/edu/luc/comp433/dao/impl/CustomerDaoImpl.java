/**
 * 
 */
package edu.luc.comp433.dao.impl;

import javax.persistence.NoResultException;

import edu.luc.comp433.dao.CustomerDao;
import edu.luc.comp433.model.Customer;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class CustomerDaoImpl extends BaseDaoImpl<Short, Customer> implements
		CustomerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.luc.comp433.dao.CustomerDao#findCustomerByLogin(java.lang.String)
	 */
	@Override
	public Customer findByLogin(String login) {
		String query = "SELECT customer FROM Customer "
				+ "customer WHERE customer.login = :login";
		try {
			return getEntityManager().createQuery(query, Customer.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean validateUserAuthentication(String login, String password) {
		String query = "SELECT customer.id FROM Customer "
				+ "customer WHERE customer.login = :login "
				+ "AND customer.password = :passwd";

		try {
			getEntityManager().createQuery(query, Short.class)
					.setParameter("login", login)
					.setParameter("passwd", password).getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}
