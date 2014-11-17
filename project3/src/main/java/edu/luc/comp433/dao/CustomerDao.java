/**
 * 
 */
package edu.luc.comp433.dao;

import edu.luc.comp433.model.Customer;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface CustomerDao extends BaseDao<Short, Customer> {

	/**
	 * @param login
	 * @return
	 */
	Customer findByLogin(String login);

}

