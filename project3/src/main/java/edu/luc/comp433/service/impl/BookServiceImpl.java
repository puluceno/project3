package edu.luc.comp433.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import edu.luc.comp433.dao.BookDAO;
import edu.luc.comp433.model.Book;
import edu.luc.comp433.service.BookService;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
@Path("/books")
public class BookServiceImpl extends BaseServiceImpl<Short, Book> implements
		BookService {

	BookDAO bookDAO = new BookDAO();

	@Override
	@GET
	@Produces("application/json")
	public List<Book> listAllBooks(@QueryParam("ids") List<Short> ids,
			@QueryParam("title") String title,
			@QueryParam("author") String author,
			@DefaultValue("0") @QueryParam("minPrice") BigDecimal minPrice,
			@DefaultValue("9999") @QueryParam("maxPrice") BigDecimal maxPrice) {
		if (!ids.isEmpty())
			return bookDAO.findById(ids);
		if (title != null)
			return bookDAO.searchByTitle(title.toLowerCase());
		if (author != null)
			return bookDAO.searchByAuthor(author.toLowerCase());
		if (minPrice != null || maxPrice != null)
			return bookDAO.searchByPrice(minPrice, maxPrice);

		return bookDAO.findAll();
	}
}