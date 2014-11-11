package edu.luc.comp433.service;

import java.math.BigDecimal;
import java.util.List;

import edu.luc.comp433.model.Book;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */

public interface BookService extends BaseService<Short, Book> {

	public List<Book> listAllBooks(List<Short> ids, String title,
			String author, BigDecimal minPrice, BigDecimal maxPrice);
}