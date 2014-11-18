/**
 * 
 */
package edu.luc.comp433.service.workflow;

import java.math.BigDecimal;
import java.util.List;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.dao.impl.OrderDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Book;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.model.Order;
import edu.luc.comp433.model.enumerator.OrderStatus;
import edu.luc.comp433.service.exception.OrderNotFoundException;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class OrderActivity {

	private OrderDao orderDao = new OrderDaoImpl();
	private CustomerActivity customerActivity = new CustomerActivity();
	private AddressActivity addressActivity = new AddressActivity();

	public Order createOrder(Order order) {
		try {
			/*
			 * Computing the total amount of the order and adding the new order
			 * to each book order list
			 */
			BigDecimal amount = new BigDecimal(0);
			for (Book book : order.getBookList()) {
				amount = amount.add(book.getPrice());
				book.getOrderList().add(order);
			}
			order.getPayment().setAmount(amount);
			Customer customer = order.getCustomer();
			Address address = order.getAddress();

			/*
			 * Verifies if the customer is already registered. In this case we
			 * try to reuse an existing address to delivery.
			 */
			if (customerActivity.validateUserAuth(customer)) {
				customer = customerActivity.findCustomerByLogin(customer
						.getLogin());
				address = addressActivity
						.findAddressByCustomerIdAndAddressInformation(
								customer.getId(), order.getAddress());

				/* If it is a new address, update customer's address list */
				if (null == address.getId()) {
					customer.getAddressList().add(address);
					customer = new CustomerActivity().update(customer);
				}
			} else {
				customer.getAddressList().add(address);
				customer = new CustomerActivity().create(customer);
			}

			/*
			 * Retrieves the attached instance of the address to be used as the
			 * order delivery address
			 */
			address = addressActivity
					.findAddressByCustomerIdAndAddressInformation(
							customer.getId(), address);

			order.setStatus(OrderStatus.PROCESSING);
			order.setCustomer(customer);
			customer.getOrderList().add(order);
			order.setAddress(address);
			address.getOrderList().add(order);
			order.setPayment(order.getPayment());
			orderDao.getEntityManager().getTransaction().begin();
			orderDao.merge(order);
			orderDao.getEntityManager().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			orderDao.getEntityManager().getTransaction().rollback();
			throw new RuntimeException("Error while trying to create order.");
		}
		return order;
	}

	public Boolean cancelOrder(Short orderId) throws OrderNotFoundException {
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

	public OrderStatus checkOrderStatus(Short orderId)
			throws OrderNotFoundException {
		Order toCheck = orderDao.findById(orderId);
		if (null == toCheck)
			throw new OrderNotFoundException();
		return toCheck.getStatus();
	}

	public List<Order> findOrderByCustomerLogin(String login)
			throws OrderNotFoundException {
		// TODO Review logic inside this method
		List<Order> orders = orderDao.findOrderByCustomerLogin(login);
		for (Order order : orders) {
			order.setCustomer(null);
		}
		if (null == orders || orders.isEmpty())
			throw new OrderNotFoundException();
		return orders;
	}

}
