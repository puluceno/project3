/**
 * 
 */
package edu.luc.comp433.service.workflow;

import java.util.List;

import edu.luc.comp433.dao.PaymentDao;
import edu.luc.comp433.dao.impl.PaymentDaoImpl;
import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class PaymentActivity {

	private PaymentDao paymentDao = new PaymentDaoImpl();

	public Payment findPaymentById(Short paymentId) {
		return paymentDao.findById(paymentId);
	}
		
	public List<Payment> findPaymentByCustomerId(Short customerId) {
		return paymentDao.findPaymentsByCustomer(customerId);
	}	

}
