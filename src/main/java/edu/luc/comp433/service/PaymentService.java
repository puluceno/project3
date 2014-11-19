/**
 *
 */
package edu.luc.comp433.service;

import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
public interface PaymentService extends BaseService<Short, Payment> {

	public Response findPaymentById(Short paymentId);

	public Response findPaymentByCustomerId(Short customerId);

}
