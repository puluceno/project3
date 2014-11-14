/**
 *
 */
package edu.luc.comp433.service;

import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
public interface AddressService extends BaseService<Short, Address> {

	public Response findAddressById(Short addressId);

	public Response findAddressByCustomerId(Short customerId);

}
