/**
 *
 */
package edu.luc.comp433.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import edu.luc.comp433.dao.PaymentDao;
import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class PaymentDaoImpl extends BaseDaoImpl<Short, Payment> implements
		PaymentDao {

	@Override
	public List<Payment> findPaymentsByCustomer(Short customerId) {
		String query = "SELECT payment FROM Payment "
				+ "payment WHERE payment.customer.id = :customerId";
		try {
			return getEntityManager().createQuery(query, Payment.class)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
