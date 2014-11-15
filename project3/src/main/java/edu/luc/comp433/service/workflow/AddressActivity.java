/**
 *
 */
package edu.luc.comp433.service.workflow;

import java.util.ArrayList;
import java.util.List;

import edu.luc.comp433.dao.AddressDao;
import edu.luc.comp433.dao.impl.AddressDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.service.representation.AddressRepresentation;

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
	public AddressRepresentation findAddressById(Short addressId) {
		return toRepresentation(addressDao.findById(addressId));
	}

	/**
	 *
	 * @param customerId
	 * @return
	 */
	public List<AddressRepresentation> findAddressByCustomerId(Short customerId) {
		return toRepresentationList(addressDao
				.findAddressByCustomerId(customerId));
	}

	/**
	 *
	 * @param addresses
	 * @return
	 */
	private List<AddressRepresentation> toRepresentationList(
			List<Address> addresses) {
		List<AddressRepresentation> addressesRep = new ArrayList<AddressRepresentation>();
		for (Address address : addresses) {
			addressesRep.add(toRepresentation(address));
		}
		return addressesRep;
	}

	/**
	 *
	 * @param address
	 * @return
	 */
	private AddressRepresentation toRepresentation(Address address) {
		return new AddressRepresentation(address.getId(), address.getState(),
				address.getNumber(), address.getZipcode(), address.getCity(),
				address.getState());
	}

}
