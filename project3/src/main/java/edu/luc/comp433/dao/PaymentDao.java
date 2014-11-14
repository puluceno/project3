/**
 *
 */
package edu.luc.comp433.dao;

import java.util.List;

import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface PaymentDao extends BaseDao<Short, Payment> {

	List<Payment> findPaymentsByCustomer(Short customerId);

}
