/**
 *
 */
package edu.luc.comp433.service.impl;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.luc.comp433.dao.PaymentDao;
import edu.luc.comp433.dao.impl.PaymentDaoImpl;
import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/payments")
public class PaymentServiceImpl {

	private PaymentDao paymentDao = new PaymentDaoImpl();

	@GET
	@Produces("{application/json,application/xml}")
	public Payment findPaymentById(@QueryParam("paymentId") Short paymentId) {
		return paymentDao.findById(paymentId);
	}

	@GET
	@Produces("{application/json,application/xml}")
	public List<Payment> findPaymentByCustomerId(
			@QueryParam("customerId") Short customerId) {
		return Collections.emptyList();
	}

}
