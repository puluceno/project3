package edu.luc.comp433.dao;

import java.util.List;

import javax.persistence.EntityManager;

import edu.luc.comp433.model.BaseEntity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface BaseDao<I extends Number, E extends BaseEntity<I>> {

	E findById(I id);
	Boolean exists(E entity);
	List<E> findAll();
    List<E> findAll(int start, int max);
    void persist(E entity);
    void remove(E entity);
    E merge(E entity);
    E attach(E entity);
    void flush();
    EntityManager getEntityManager();
    
}

