/**
 * 
 */
package edu.luc.comp433.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import edu.luc.comp433.dao.BaseDao;
import edu.luc.comp433.model.BaseEntity;
import edu.luc.comp433.util.PersistenceManager;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class BaseDaoImpl<I extends Number, E extends BaseEntity<I>> implements
		BaseDao<I, E> {

	private Class<E> clazz;
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.clazz = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public E findById(I id) {
		try {
			return getEntityManager().find(clazz, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<E> findById(List<I> ids) {
		String entityName = clazz.getSimpleName();
		String query = "SELECT entity FROM " + entityName
				+ " entity WHERE entity.id in :idsList";
		try {
			return getEntityManager()
					.createQuery(query, clazz)
					.setParameter("idsList", ids).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<E> findAll() {
		String entityName = clazz.getSimpleName();
		String query = "SELECT entity FROM " + entityName + " entity";
		try {
			return getEntityManager().createQuery(query, clazz).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<E> findAll(int start, int max) {
		String entityName = clazz.getSimpleName();
		String query = "SELECT entity" + " FROM " + entityName + " entity";

		try {
			return getEntityManager().createQuery(query, clazz)
					.setFirstResult(start).setMaxResults(max).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public void persist(E entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public void remove(E entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public E merge(E entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public E attach(E entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	public EntityManager getEntityManager() {
		if (null == em)
			em = PersistenceManager.getInstance().getEntityManager();
		return em;
	}

	@Override
	public Boolean exists(E entity) {
		return (null == findById(entity.getId())) ? false : true;
	}

}

