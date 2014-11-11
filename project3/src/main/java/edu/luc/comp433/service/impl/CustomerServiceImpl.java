/**
 *
 */
package edu.luc.comp433.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.comp433.dao.CustomerDao;
import edu.luc.comp433.dao.impl.CustomerDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.model.Payment;
import edu.luc.comp433.service.CustomerService;
import edu.luc.comp433.service.exception.InvalidAddressException;
import edu.luc.comp433.service.exception.InvalidPaymentException;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
@Path("/customers")
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao = new CustomerDaoImpl();

	@Override
	@GET
	@Path("/{login}")
	@Produces("{application/json,application/xml}")
	public Customer findCustomerByLogin(@PathParam("login") String login) {
		return customerDao.findByLogin(login);
	}

	@Override
	@POST
	@Consumes("{application/json, application/xml}")
	// TODO: FIX the payload because it just support one incoming object
	public Response createCustomer(Customer customer, Address address,
			Payment payment) {

		try {
			customerDao.getEntityManager().getTransaction().begin();
			if (null == address)
				throw new InvalidAddressException();

			customer.getAddressList().add(address);
			address.setCustomer(customer);

			if (null == payment)
				throw new InvalidPaymentException();

			customer.getPaymentList().add(payment);
			payment.setCustomer(customer);

			if (null != customer.getId()) {
				customer = customerDao.attach(customer);
				customerDao.merge(customer);
			} else {
				customerDao.persist(customer);
			}
			customerDao.getEntityManager().getTransaction().commit();
			return Response.status(201).entity(customerDao.attach(customer))
					.build();

		} catch (Exception e) {
			e.printStackTrace();
			customerDao.getEntityManager().getTransaction().rollback();
			return null;
		}
	}

	@Override
	@PUT
	@Path("/{customerId}")
	@Produces("{application/json,application/xml}")
	@Consumes("{application/json, application/xml}")
	public Customer updateCustomer(@PathParam("customerId") Short customerId,
			Customer customer) {
		// TODO CREATE UPDATE METHOD
		return null;
	}
}
