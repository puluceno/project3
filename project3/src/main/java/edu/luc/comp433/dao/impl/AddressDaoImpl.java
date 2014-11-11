package edu.luc.comp433.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import edu.luc.comp433.dao.AddressDao;
import edu.luc.comp433.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class AddressDaoImpl extends BaseDaoImpl<Short, Address> implements
		AddressDao {

	@Override
	public List<Address> findAddressByCustomerId(Short customerId) {
		String query = "SELECT address FROM Address "
				+ "address WHERE address.customer.id= :customerId";
		try {
			return getEntityManager().createQuery(query, Address.class)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

}

