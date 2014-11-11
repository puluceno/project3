/**
 * 
 */
package edu.luc.comp433.dao;

import java.util.List;

import edu.luc.comp433.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface AddressDao extends BaseDao<Short, Address> {

	/**
	 * @param customerId
	 * @return
	 */
	List<Address> findAddressByCustomerId(Short customerId);

}

