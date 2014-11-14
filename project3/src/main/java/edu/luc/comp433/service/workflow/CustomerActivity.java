/**
 *
 */
package edu.luc.comp433.service.workflow;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.dao.CustomerDao;
import edu.luc.comp433.dao.impl.CustomerDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class CustomerActivity {

	private CustomerDao customerDao = new CustomerDaoImpl();

	public Response findByLogin(String login) {
		Customer customer = null;
		Status status = Status.NOT_FOUND;

		if (null == login)
			status = Status.EXPECTATION_FAILED;
		else {
			customer = customerDao.findByLogin(login);
			if (null != customer)
				status = Status.OK;
		}

		return Response.status(status).entity(customer).build();
	}

	public Response create(Customer customer) {
		return createOrUpdate(customer, true);
	}

	public Response update(Short customerId, Customer customer) {
		return createOrUpdate(customer, false);
	}

	private Response createOrUpdate(Customer customer, boolean create) {
		Response response = null;
		try {
			if (validateCustomer(customer)) {
				createCustomerRelations(customer);
				customerDao.getEntityManager().getTransaction().begin();
				if (create) {
					customerDao.persist(customer);
					customer = customerDao.attach(customer);
					response = Response.created(
							URI.create("/customers/" + customer.getLogin()))
							.build();
				} else {
					customer = customerDao.attach(customer);
					customerDao.merge(customer);
					response = Response.ok(customer).build();
				}
				customerDao.getEntityManager().getTransaction().commit();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			customerDao.getEntityManager().getTransaction().rollback();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	private boolean validateCustomer(Customer customer) {
		return !(customer == null || customer.getAddressList() == null
				|| customer.getAddressList().isEmpty()
				|| customer.getPaymentList() == null || customer
				.getPaymentList().isEmpty());
	}

	private void createCustomerRelations(Customer customer) {
		for (Address address : customer.getAddressList()) {
			address.setCustomer(customer);
		}

		for (Payment payment : customer.getPaymentList()) {
			payment.setCustomer(customer);
		}
	}

}
