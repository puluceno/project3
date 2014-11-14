/**
 * 
 */
package edu.luc.comp433.service.workflow;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.dao.impl.OrderDaoImpl;
import edu.luc.comp433.model.Order;
import edu.luc.comp433.model.enumerator.OrderStatus;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class OrderActivity {

	private OrderDao orderDao = new OrderDaoImpl();

	public Response createOrder(Order order) {
		Response response = null;
		try {
			if (null != order) {
				orderDao.getEntityManager().getTransaction().begin();
				order = orderDao.merge(order);
				orderDao.getEntityManager().getTransaction().commit();
				order = orderDao.attach(order);
				response = Response.created(
						URI.create("/orders/" + order.getId())).build();
			} else {
//				TODO verify the status code!
				response = Response.status(Status.BAD_REQUEST).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			orderDao.getEntityManager().getTransaction().rollback();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return response;
	}

	public Boolean cancelOrder(Short orderId) {
		try {
			if (null != orderId) {
				Order toCancel = orderDao.findById(orderId);
				if (null != toCancel
						&& toCancel.getStatus().equals(OrderStatus.PROCESSING)) {
					toCancel.setStatus(OrderStatus.CANCELED);
					orderDao.getEntityManager().getTransaction().begin();
					orderDao.merge(toCancel);
					orderDao.getEntityManager().getTransaction().commit();

					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Response checkOrderStatus(Short orderId) {
		Response response = null;
		if (null != orderId) {
			Order toCheck = orderDao.findById(orderId);
			if (null != toCheck)
				response = Response.status(Status.OK)
						.entity(toCheck.getStatus()).build();
			else
				response = Response.status(Status.NOT_FOUND).build();
		} else {
			response = Response.status(Status.EXPECTATION_FAILED).build();
		}
		return response;
	}

	public List<Order> findOrderByCustomerLogin(String login) {
		return orderDao.findOrderByCustomerLogin(login);
	}
}
