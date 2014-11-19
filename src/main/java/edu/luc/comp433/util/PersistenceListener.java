/**
 * 
 */
package edu.luc.comp433.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class PersistenceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PersistenceManager.getInstance().closeEntityManagerFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

}

