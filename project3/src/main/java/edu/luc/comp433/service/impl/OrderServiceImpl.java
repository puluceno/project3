package edu.luc.comp433.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.dao.impl.OrderDaoImpl;
import edu.luc.comp433.model.Order;
import edu.luc.comp433.model.enumerator.OrderStatus;
import edu.luc.comp433.service.OrderService;
import edu.luc.comp433.service.exception.OrderNotFoundException;

/**
 *
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/orders")
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImpl();

	// private CustomerService customerService = new CustomerServiceImpl();

	@Override
	@POST
	@Consumes("{application/json, application/xml}")
	public Response createOrder(Order order) {

		// try {
		// if (null == books)
		// books = Collections.emptyList();
		//
		// Order order = new Order();
		// order.getBookList().addAll(books);
		//
		// BigDecimal amount = new BigDecimal(0);
		//
		// for (Book book: books) {
		// amount = amount.add(book.getPrice());
		// book.getOrderList().add(order);
		// }
		//
		// payment.setAmount(amount);
		// order.setCustomer(customer);
		// order.setAddress(address);
		// order.setPayment(payment);
		// order.setStatus(OrderStatus.PROCESSING);
		//
		// customer = customerService.createOrUpdateCustomer(customer, address,
		// payment);
		// orderDao.getEntityManager().getTransaction().begin();
		// order = orderDao.merge(order);
		// orderDao.getEntityManager().getTransaction().commit();
		//
		// return order.getId();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// orderDao.getEntityManager().getTransaction().rollback();
		// return null;
		// }
		// TODO: FIX THIS
		return Response.status(200).build();
	}

	@Override
	@PUT
	@Path("/{orderId:[0-9][0-9]*}")
	public Boolean cancelOrder(@PathParam("orderId") Short orderId) {
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

	@Override
	@GET
	@Path("/{orderId:[0-9][0-9]*}")
	@Produces("{application/json,application/xml}")
	public Response checkOrderStatus(@PathParam("orderId") Short orderId) {
		if (null != orderId) {
			Order toCheck = orderDao.findById(orderId);
			if (null != toCheck)
				return Response.status(Status.OK).entity(toCheck.getStatus())
						.build();
		}

		throw new OrderNotFoundException(orderId);
	}

	@Override
	@GET
	@Path("/{login}")
	@Produces("{application/json,application/xml}")
	public List<Order> findOrderByCustomerLogin(@PathParam("login") String login) {
		return orderDao.findOrderByCustomerLogin(login);
	}

}
