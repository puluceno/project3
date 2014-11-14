/**
 *
 */
package edu.luc.comp433.service.workflow;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.dao.AddressDao;
import edu.luc.comp433.dao.impl.AddressDaoImpl;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */

public class AddressActivity {

	private AddressDao addressDao = new AddressDaoImpl();

	public Response findAddressById(Short addressId) {
		return Response.status(Status.OK)
				.entity(addressDao.findById(addressId)).build();
	}

	public Response findAddressByCustomerId(Short customerId) {
		return Response.status(Status.OK)
				.entity(addressDao.findAddressByCustomerId(customerId)).build();
	}

}
