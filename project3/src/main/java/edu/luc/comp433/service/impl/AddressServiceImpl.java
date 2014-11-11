/**
 *
 */
package edu.luc.comp433.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.luc.comp433.dao.AddressDao;
import edu.luc.comp433.dao.impl.AddressDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.service.AddressService;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/address")
public class AddressServiceImpl implements AddressService {

	private AddressDao addressDao = new AddressDaoImpl();

	@Override
	@GET
	@Path("/{addressId}")
	@Produces("{application/json,application/xml}")
	public Address findAddressById(@PathParam("addressId") Short addressId) {
		return addressDao.findById(addressId);
	}

	@Override
	@GET
	@Path("/customers/{customerId}")
	@Produces("{application/json,application/xml}")
	public List<Address> findAddressByCustomerId(
			@PathParam("customerId") Short customerId) {
		return addressDao.findAddressByCustomerId(customerId);
	}

}
