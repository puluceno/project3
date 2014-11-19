/**
 *
 */
package edu.luc.comp433.service;

import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Customer;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
public interface CustomerService extends BaseService<Short, Customer> {

	/**
	 * Method responsible for returning customer data once his login is
	 * provided.
	 *
	 * @param login
	 *            User login
	 * @return All customer data.
	 */
	public Response findByLogin(String login);

	/**
	 * @param customer
	 * @param address
	 * @param payment
	 * @return
	 */
	public Response create(Customer customer);

	public Response update(Customer customer);

}
