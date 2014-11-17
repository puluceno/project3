/**
 * 
 */
package edu.luc.comp433.service.workflow;

import java.math.BigDecimal;
import java.util.List;

import edu.luc.comp433.dao.OrderDao;
import edu.luc.comp433.dao.impl.OrderDaoImpl;
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

	public Order createOrder(Order order) {
		try {
			/* Computing the total amount of the order and
			 *  adding the new order to each book order list 
			 */
			BigDecimal amount = new BigDecimal(0);
			for (Book book : order.getBookList()) {
				amount = amount.add(book.getPrice());
				book.getOrderList().add(order);
			}
			order.getPayment().setAmount(amount);
			Customer customer = order.getCustomer();
			customer.getAddressList().add(order.getAddress());
			customer.getPaymentList().add(order.getPayment());
			customer = new CustomerActivity().createOrUpdate(customer);
			
			order.setStatus(OrderStatus.PROCESSING);
			order.setCustomer(customer);
			order.setAddress(customer.getAddressList().get(0));
			order.setPayment(customer.getPaymentList().get(0));
			orderDao.getEntityManager().getTransaction().begin();
			order = orderDao.merge(order);
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
		if (null == orders || orders.isEmpty())
			throw new OrderNotFoundException();
		return orders;
	}

	// /**
	// * @param orderRequest
	// * @return
	// * @throws InvalidPaymentException
	// * @throws InvalidAddressException
	// */
	// private Order createOrderFromOrderRequest(OrderRequest orderRequest)
	// throws InvalidAddressException, InvalidPaymentException {
	//
	// Address address = new AddressActivity().findAddressById(orderRequest
	// .getAddressId());
	// Payment payment = new PaymentActivity().findPaymentById(orderRequest
	// .getPaymentId());
	// Customer customer = new CustomerActivity()
	// .findCustomerByLogin(orderRequest.getCustomerLogin());
	//
	// new CustomerActivity().create(customer);
	//
	// List<Book> books = new BookActivity().findBookByIds(orderRequest
	// .getBookIdList());
	//
	// Order order = new Order();
	// order.getBookList().addAll(books);
	//
	// BigDecimal amount = new BigDecimal(0);
	//
	// for (Book book : books) {
	// amount = amount.add(book.getPrice());
	// book.getOrderList().add(order);
	// }
	//
	// payment.setAmount(amount);
	// order.setCustomer(customer);
	// order.setAddress(address);
	// order.setPayment(payment);
	// order.setStatus(OrderStatus.PROCESSING);
	// // TODO Finalize
	// // customer = new CustomerActivity().create(customer, address, payment);
	// // orderDao.getEntityManager().getTransaction().begin();
	// // order = orderDao.merge(order);
	// // orderDao.getEntityManager().getTransaction().commit();
	//
	// return order;
	// }

}
