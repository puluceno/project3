/**
 *
 */
package edu.luc.comp433.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class PersistenceManager {

	public static final String PU_NAME = "project3-ds";
	private static final PersistenceManager singleton = new PersistenceManager();
	protected EntityManagerFactory eMFactory;

	public static PersistenceManager getInstance() {
		return singleton;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if (null == eMFactory) {
			eMFactory = Persistence.createEntityManagerFactory(PU_NAME);
		}
		return eMFactory;
	}

	public EntityManager getEntityManager() {
		return PersistenceManager.getInstance().getEntityManagerFactory()
				.createEntityManager();
	}

	public void closeEntityManagerFactory() {
		if (null != eMFactory) {
			eMFactory.close();
			eMFactory = null;
		}
	}

}
