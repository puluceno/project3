/**
 *
 */
package edu.luc.comp433.service.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.service.AddressService;
import edu.luc.comp433.service.workflow.AddressActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/address")
public class AddressResource implements AddressService {

	AddressActivity addressActivity = new AddressActivity();

	@Override
	@GET
	@Path("/{addressId}")
	@Produces({ "application/json", "application/xml" })
	public Response findAddressById(@PathParam("addressId") Short addressId) {
		if (addressId == null)
			throw new WebApplicationException(400);
		return Response.status(Status.OK)
				.entity(addressActivity.findAddressById(addressId)).build();
	}

	@Override
	@GET
	@Path("/customers/{customerId}")
	@Produces({ "application/json", "application/xml" })
	public Response findAddressByCustomerId(
			@PathParam("customerId") Short customerId) {
		if (customerId == null)
			throw new WebApplicationException(400);
		return Response.status(Status.OK)
				.entity(addressActivity.findAddressByCustomerId(customerId))
				.build();
	}
}
