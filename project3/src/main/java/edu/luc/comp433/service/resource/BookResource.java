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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.comp433.model.Book;
import edu.luc.comp433.service.BookService;
import edu.luc.comp433.service.workflow.BookActivity;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
@Path("/books")
public class BookResource extends BaseResource<Short, Book> implements
		BookService {

	private BookActivity bookActivity = new BookActivity();

	@Override
	@GET
	@Path("{id}")
	@Produces({ "application/json", "application/xml" })
	public Response retrieve(@PathParam("id") Short id) {

		if (null == id)
			throw new WebApplicationException(400);

		Book book = bookActivity.searchById(id);

		if (null == book)
			throw new WebApplicationException(404);

		return Response.status(Status.OK).entity(book).build();
	}

	@Override
	@GET
	@Produces({ "application/json", "application/xml" })
	public Response retrieve(@QueryParam("id") List<Short> ids,
			@QueryParam("title") String title,
			@QueryParam("author") String author,
			@DefaultValue("0") @QueryParam("minPrice") BigDecimal minPrice,
			@DefaultValue("9999") @QueryParam("maxPrice") BigDecimal maxPrice) {

		List<Book> books = bookActivity.genericSearch(ids, title, author,
				minPrice, maxPrice);

		if (books.isEmpty())
			throw new WebApplicationException(404);

		return Response.status(Status.OK).entity(books).build();
	}
}