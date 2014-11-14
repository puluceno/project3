package edu.luc.comp433.service;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import edu.luc.comp433.model.Book;
import edu.luc.comp433.service.representation.BookRepresentation;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
@WebService
public interface BookService extends BaseService<Short, Book> {

	public BookRepresentation retrieve(Short id);

	public List<BookRepresentation> retrieve(String title, String author,
			BigDecimal minPrice, BigDecimal maxPrice);

}