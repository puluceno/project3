/**
 *
 */
package edu.luc.comp433.service.workflow;

import java.util.List;

import edu.luc.comp433.dao.AddressDao;
import edu.luc.comp433.dao.impl.AddressDaoImpl;
import edu.luc.comp433.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */

public class AddressActivity {

	private AddressDao addressDao = new AddressDaoImpl();

	/**
	 *
	 * @param addressId
	 * @return
	 */
	public Address findAddressById(Short addressId) {
		return addressDao.findById(addressId);
	}

	/**
	 *
	 * @param customerId
	 * @return
	 */
	public List<Address> findAddressByCustomerId(Short customerId) {
		return addressDao.findAddressByCustomerId(customerId);
	}

}
