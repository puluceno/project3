/**
 *
 */
package edu.luc.comp433.service;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@WebService
public interface PaymentService extends BaseService<Short, Payment> {

	public Response findPaymentById(Short paymentId);

	public Response findPaymentByCustomerId(Short customerId);

}
