/**
 * 
 */
package edu.luc.comp433.service.workflow;

import edu.luc.comp433.dao.CustomerDao;
import edu.luc.comp433.dao.impl.CustomerDaoImpl;
import edu.luc.comp433.model.Address;
import edu.luc.comp433.model.Customer;
import edu.luc.comp433.service.exception.InvalidAddressException;
import edu.luc.comp433.service.exception.InvalidPaymentException;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class CustomerActivity {

	private CustomerDao customerDao = new CustomerDaoImpl();

	public Customer findCustomerByLogin(String login) {
		return customerDao.findByLogin(login);
	}

	public Customer create(Customer customer) throws InvalidAddressException,
			InvalidPaymentException {
		return createOrUpdate(customer);
	}

	public Customer update(Customer customer) throws InvalidAddressException,
			InvalidPaymentException {
		return createOrUpdate(customer);
	}

	public Customer createOrUpdate(Customer customer)
			throws InvalidAddressException, InvalidPaymentException {
		validateAddressAndPayment(customer);
		try {
			createCustomerRelations(customer);
			customerDao.getEntityManager().getTransaction().begin();
			if (null != customer.getId()) {
				customer = customerDao.attach(customer);
				customerDao.merge(customer);
			} else {
				customerDao.persist(customer);
			}
			customerDao.getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			customerDao.getEntityManager().getTransaction().rollback();
		}
		return customer;
	}

	private void validateAddressAndPayment(Customer customer)
			throws InvalidAddressException {
		if (customer == null || customer.getAddressList() == null
				|| customer.getAddressList().isEmpty())
			throw new InvalidAddressException();
	}

	private void createCustomerRelations(Customer customer)
			throws InvalidAddressException {
		if (null == customer.getAddressList()
				|| customer.getAddressList().isEmpty())
			throw new InvalidAddressException();

		for (Address address : customer.getAddressList()) {
			address.setCustomer(customer);
		}
	}

	/**
	 * @param customer
	 * @return
	 */
	public boolean validateUserAuth(Customer customer) {
		return customerDao.validateUserAuthentication(customer.getLogin(),
				customer.getPassword());
	}

}
