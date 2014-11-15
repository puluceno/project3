/**
 *
 */
package edu.luc.comp433.service.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.luc.comp433.dao.BookDAO;
import edu.luc.comp433.model.Book;
import edu.luc.comp433.service.representation.BookRepresentation;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class BookActivity {

	BookDAO bookDAO = new BookDAO();

	/**
	 *
	 * @param id
	 * @return
	 */
	public BookRepresentation searchById(Short id) {
		BookRepresentation bookRep = null;
		Book book = (null != id) ? bookDAO.findById(id) : null;

		if (null != book)
			bookRep = new BookRepresentation(book.getId(), book.getTitle(),
					book.getAuthor(), book.getPrice());

		return bookRep;
	}

	/**
	 * @param title
	 * @param author
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<BookRepresentation> genericSearch(String title, String author,
			BigDecimal minPrice, BigDecimal maxPrice) {
		List<Book> books = new ArrayList<Book>();

		if (null != title)
			books.addAll(bookDAO.searchByTitle(title));
		else if (null != author)
			books.addAll(bookDAO.searchByAuthor(author));
		else
			books.addAll(bookDAO.searchByPrice(minPrice, maxPrice));
		return toRepresentation(books);
	}

	/**
	 * @param books
	 * @return
	 */
	private List<BookRepresentation> toRepresentation(List<Book> books) {
		List<BookRepresentation> booksRep = new ArrayList<BookRepresentation>();
		for (Book book : books) {
			BookRepresentation bookRep = new BookRepresentation(book.getId(),
					book.getTitle(), book.getAuthor(), book.getPrice());
			booksRep.add(bookRep);
		}
		return booksRep;
	}
}
