/**
 *
 */
package edu.luc.comp433.service.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.model.Customer;
import edu.luc.comp433.service.CustomerService;
import edu.luc.comp433.service.exception.InvalidAddressException;
import edu.luc.comp433.service.exception.InvalidPaymentException;
import edu.luc.comp433.service.workflow.CustomerActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
@Path("/customers")
public class CustomerResource implements CustomerService {

	private CustomerActivity customerActivity = new CustomerActivity();

	@Override
	@GET
	@Path("/{login}")
	@Produces({ "application/json", "application/xml" })
	public Response findByLogin(@PathParam("login") String login) {
		Customer customer = null;
		Response response = null;

		if (null != login) {
			customer = customerActivity.findCustomerByLogin(login);
			if (null != customer)
				response = Response.ok().entity(customer).build();
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

	@Override
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Response create(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.create(customer);
			response = Response
					.created(URI.create("/customers/" + customer.getLogin()))
					.entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (InvalidPaymentException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	@PUT
	@Path("/{customerId}")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Response update(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.update(customer);
			response = Response.ok().entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (InvalidPaymentException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
}
