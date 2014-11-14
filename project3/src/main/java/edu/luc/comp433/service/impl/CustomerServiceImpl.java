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
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.dao.CustomerDao;
import edu.luc.comp433.dao.impl.CustomerDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.model.Payment;
import edu.luc.comp433.service.CustomerService;

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
	@Produces("application/json")
	public Customer findCustomerByLogin(@PathParam("login") String login) {
		return customerDao.findByLogin(login);
	}

	@Override
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createCustomer(Customer customer) {

		try {
			customerDao.getEntityManager().getTransaction().begin();
			if (customer == null || customer.getAddressList() == null
					|| customer.getAddressList().isEmpty()
					|| customer.getPaymentList() == null
					|| customer.getPaymentList().isEmpty())
				return Response.status(Status.EXPECTATION_FAILED).build();

			for (Address address : customer.getAddressList()) {
				address.setCustomer(customer);
			}

			for (Payment payment : customer.getPaymentList()) {
				payment.setCustomer(customer);
			}

			if (customer.getId() != null) {
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
	@Consumes("application/json")
	@Produces("application/json")
	public Customer updateCustomer(@PathParam("customerId") Short customerId,
			Customer customer) {
		// TODO CREATE UPDATE METHOD
		return null;
	}
}
