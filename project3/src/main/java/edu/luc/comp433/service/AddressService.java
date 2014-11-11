/**
 *
 */
package edu.luc.comp433.service;

import java.util.List;

import edu.luc.comp433.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */

public interface AddressService extends BaseService<Short, Address> {

	public Address findAddressById(Short addressId);

	public List<Address> findAddressByCustomerId(Short customerId);

}
