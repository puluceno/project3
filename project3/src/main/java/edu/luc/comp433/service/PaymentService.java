/**
 *
 */
package edu.luc.comp433.service;

import java.util.List;

import javax.jws.WebService;

import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@WebService
public interface PaymentService extends BaseService<Short, Payment> {

	public Payment findPaymentById(Short paymentId);

	public List<Payment> findPaymentByCustomerId(Short customerId);

}
