/**
 * 
 */
package edu.luc.comp433.dao;

import java.util.List;

import edu.luc.comp433.model.Order;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface OrderDao extends BaseDao<Short, Order> {

	/**
	 * @param login
	 * @return
	 */
	List<Order> findOrderByCustomerLogin(String login);

}

