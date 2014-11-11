/**
 *
 */
package edu.luc.comp433.service;

import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */

public interface CustomerService extends BaseService<Short, Customer> {

	public Customer findCustomerByLogin(String login);

	/**
	 * @param customer
	 * @param address
	 * @param payment
	 * @return
	 */
	public Response createCustomer(Customer customer, Address address,
			Payment payment);

	public Customer updateCustomer(Short customerId, Customer customer);

}
