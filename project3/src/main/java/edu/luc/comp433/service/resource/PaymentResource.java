/**
 *
 */
package edu.luc.comp433.service.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.model.Payment;
import edu.luc.comp433.service.PaymentService;
import edu.luc.comp433.service.workflow.PaymentActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/payments")
public class PaymentResource implements PaymentService {

	private PaymentActivity paymentActivity = new PaymentActivity();

	@GET
	@Path("{id}")
	@Produces({ "application/json", "application/xml" })
	public Response findPaymentById(@PathParam("id") Short id) {
		Response response = null;
		if (null != id) {
			Payment payment = paymentActivity.findPaymentById(id);
			if (null != payment) {
				response = Response.ok(payment).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	public Response findPaymentByCustomerId(
			@QueryParam("customerId") Short customerId) {
		Response response = null;
		if (null != customerId) {
			List<Payment> payments = paymentActivity
					.findPaymentByCustomerId(customerId);
			if (null != payments && !payments.isEmpty()) {
				response = Response.ok(payments).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

}
