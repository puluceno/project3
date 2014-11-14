package edu.luc.comp433.service.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import edu.luc.comp433.model.Book;
import edu.luc.comp433.service.BookService;
import edu.luc.comp433.service.representation.BookRepresentation;
import edu.luc.comp433.service.workflow.BookActivity;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
@Path("/books")
public class BookResource extends BaseResource<Short, Book> implements
		BookService {

	@Override
	@GET
	@Path("{id}")
	@Produces({ "application/json", "application/xml" })
	public BookRepresentation retrieve(@PathParam("id") Short id) {
		if (null == id)
			throw new WebApplicationException(400);
		BookRepresentation book = new BookActivity().searchById(id);
		if (null == book)
			throw new WebApplicationException(404);
		return book;
	}

	@Override
	@GET
	@Produces({ "application/json", "application/xml" })
	public List<BookRepresentation> retrieve(@QueryParam("title") String title,
			@QueryParam("author") String author,
			@DefaultValue("0") @QueryParam("minPrice") BigDecimal minPrice,
			@DefaultValue("9999") @QueryParam("maxPrice") BigDecimal maxPrice) {

		List<BookRepresentation> books = new BookActivity().genericSearch(
				title, author, minPrice, maxPrice);

		if (books.isEmpty())
			throw new WebApplicationException(404);
		return books;
	}

}