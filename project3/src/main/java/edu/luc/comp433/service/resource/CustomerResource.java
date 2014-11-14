/**
 *
 */
package edu.luc.comp433.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Customer;
import edu.luc.comp433.service.CustomerService;
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
		return customerActivity.findByLogin(login);
	}

	@Override
	@POST
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Response create(Customer customer) {
		return customerActivity.create(customer);
	}

	@Override
	@PUT
	@Path("/{customerId}")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public Response update(@PathParam("customerId") Short customerId,
			Customer customer) {
		return customerActivity.update(customerId, customer);
	}
}
