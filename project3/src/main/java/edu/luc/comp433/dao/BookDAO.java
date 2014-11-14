package edu.luc.comp433.dao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import edu.luc.comp433.dao.impl.BaseDaoImpl;
import edu.luc.comp433.model.Book;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */

public class BookDAO extends BaseDaoImpl<Short, Book> {

	public List<Book> searchByTitle(String title) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_TITLE, Book.class)
					.setParameter("title", "%" + title.toLowerCase() + "%").getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<Book> searchByAuthor(String author) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_AUTHOR, Book.class)
					.setParameter("author", "%" + author.toLowerCase() + "%").getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public List<Book> searchByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_PRICE, Book.class)
					.setParameter("minPrice", minPrice)
					.setParameter("maxPrice", maxPrice).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

}
