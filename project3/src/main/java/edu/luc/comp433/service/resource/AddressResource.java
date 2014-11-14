/**
 *
 */
package edu.luc.comp433.service.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
	@Produces("{application/json,application/xml}")
	public Response findAddressById(@PathParam("addressId") Short addressId) {
		return addressActivity.findAddressById(addressId);
	}

	@Override
	@GET
	@Path("/customers/{customerId}")
	@Produces("{application/json,application/xml}")
	public Response findAddressByCustomerId(
			@PathParam("customerId") Short customerId) {
		return addressActivity.findAddressByCustomerId(customerId);
	}
}
