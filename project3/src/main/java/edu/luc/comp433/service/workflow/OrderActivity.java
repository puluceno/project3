/**
 * 
 */
package edu.luc.comp433.service.workflow;

import java.util.List;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.dao.impl.OrderDaoImpl;
import edu.luc.comp433.model.Order;
import edu.luc.comp433.model.enumerator.OrderStatus;
import edu.luc.comp433.service.exception.OrderNotFoundException;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class OrderActivity {

	private OrderDao orderDao = new OrderDaoImpl();

	public Order createOrder(Order order) {
		try {
			orderDao.getEntityManager().getTransaction().begin();
			orderDao.persist(order);
			order = orderDao.attach(order);
			orderDao.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			orderDao.getEntityManager().getTransaction().rollback();
			throw new RuntimeException("Error while trying to create order.");
		}
		return order;
	}

	public Boolean cancelOrder(Short orderId) throws OrderNotFoundException  {
		Order toCancel = orderDao.findById(orderId);
		if (null == toCancel)
			throw new OrderNotFoundException();
		try {
			if (toCancel.getStatus().equals(OrderStatus.PROCESSING)) {
				toCancel.setStatus(OrderStatus.CANCELED);
				orderDao.getEntityManager().getTransaction().begin();
				orderDao.merge(toCancel);
				orderDao.getEntityManager().getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			orderDao.getEntityManager().getTransaction().rollback();
			throw new RuntimeException("Error while trying to cancel order.");
		}
	}

	public OrderStatus checkOrderStatus(Short orderId) throws OrderNotFoundException {
		Order toCheck = orderDao.findById(orderId);
		if (null == toCheck)
			throw new OrderNotFoundException();
		return toCheck.getStatus();
	}

	public List<Order> findOrderByCustomerLogin(String login) throws OrderNotFoundException {
		List<Order> orders = orderDao.findOrderByCustomerLogin(login);
		if (null == orders || orders.isEmpty())
			throw new OrderNotFoundException();
		return orders;
	}
}
