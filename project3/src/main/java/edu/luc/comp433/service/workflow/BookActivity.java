/**
 *
 */
package edu.luc.comp433.service.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.luc.comp433.dao.BookDAO;
import edu.luc.comp433.model.Book;

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
	public Book searchById(Short id) {
		if (null == id)
			throw new IllegalArgumentException("id must be passed.");
		return bookDAO.findById(id);
	}

	/**
	 * @param ids
	 * @param title
	 * @param author
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<Book> genericSearch(List<Short> ids, String title,
			String author, BigDecimal minPrice, BigDecimal maxPrice) {
		List<Book> books = new ArrayList<Book>();

		if (null != ids && !ids.isEmpty())
			books.addAll(bookDAO.findById(ids));
		else if (null != title)
			books.addAll(bookDAO.searchByTitle(title));
		else if (null != author)
			books.addAll(bookDAO.searchByAuthor(author));
		else
			books.addAll(bookDAO.searchByPrice(minPrice, maxPrice));
		return books;
	}

	public List<Book> findBookByIds(List<Short> bookIdList) {
		return bookDAO.findById(bookIdList);
	}
}
