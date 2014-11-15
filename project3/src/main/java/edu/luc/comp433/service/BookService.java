package edu.luc.comp433.service;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;

import edu.luc.comp433.model.Book;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
public interface BookService extends BaseService<Short, Book> {

	public Response retrieve(Short id);

	public Response retrieve(String title, String author, BigDecimal minPrice,
			BigDecimal maxPrice);

}